package com.jeepark.onestep

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jeepark.onestep.platform.permission.PermissionRequestPolicy
import com.jeepark.onestep.ui.components.OneStepBottomBar
import com.jeepark.onestep.ui.screens.auth.AuthScreen
import com.jeepark.onestep.ui.screens.collection.CollectionScreen
import com.jeepark.onestep.ui.screens.footprints.FootprintsScreen
import com.jeepark.onestep.ui.screens.main.MainScreen
import com.jeepark.onestep.ui.screens.settings.SettingScreen
import com.jeepark.onestep.ui.screens.signup.SignupScreen
import com.jeepark.onestep.ui.screens.start.InitScreen
import com.jeepark.onestep.ui.screens.survey.InitQuestionScreen
import com.jeepark.onestep.ui.theme.OneStepTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }
        setContent {
            OneStepTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyNavGraph()
                }
            }
        }
    }
}

@Composable
fun MyNavGraph() {
    val context     = LocalContext.current
    val navController = rememberNavController()

    // 권한을 언제 물어보고 알림을 언제 예약할지는 정책 객체가 정하고, 이 화면에는 실제 요청 창(런처)만 남긴다
    val permissionPolicy = remember {
        PermissionRequestPolicy(
            context.appContainer.settingsRepository,
            context.appContainer.notificationScheduler,
        )
    }

    val notifPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> permissionPolicy.scheduleIfAllowed(granted) }

    val locationProvider = context.appContainer.locationProvider
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) locationProvider.refresh()
    }

    LaunchedEffect(Unit) {
        // 권한 요청 1회만 (회전·재구성 시 다이얼로그 반복 방지)
        if (permissionPolicy.shouldRequestPermissions()) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notifPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            // 이미 한 번 요청한 경우 권한 상태에 맞춰 위치 갱신·알림 스케줄
            locationProvider.refresh()

            val notifGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                androidx.core.content.ContextCompat.checkSelfPermission(
                    context, Manifest.permission.POST_NOTIFICATIONS
                ) == android.content.pm.PackageManager.PERMISSION_GRANTED
            } else true

            permissionPolicy.scheduleIfAllowed(notifGranted)
        }
    }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val bottomBarRoutes = setOf("main", "progress", "completed")

    // 발자취 탭(이전 퀘스트/통계): 처음 진입 시 통계가 먼저 보이고,
    // 발자취 화면에 있는 상태에서 발자취 버튼을 다시 누르면 탭이 토글된다.
    var footprintsTab by remember { mutableIntStateOf(1) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                OneStepBottomBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    onReselectFootprints = { footprintsTab = if (footprintsTab == 0) 1 else 0 }
                )
            }
        }
    ) { innerPadding ->
    NavHost(
        navController = navController,
        startDestination = "init",
        modifier = Modifier.padding(innerPadding)
    ) {

        composable(route = "init") {
            InitScreen(
                onNavigateToAuth = {
                    navController.navigate("auth") {
                        popUpTo("init") { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("init") { inclusive = true }
                    }
                },
                onNavigateToInitQuestion = {
                    navController.navigate("InitQuestion") {
                        popUpTo("init") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "auth") {
            AuthScreen(
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("auth") { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate("signup") {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "signup") {
            SignupScreen(
                onNavigateToInitQuestion = {
                    navController.navigate("InitQuestion") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onNavigateToInit = {
                    navController.navigate("init") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "InitQuestion") {
            InitQuestionScreen(
                onNavigateToMain = {
                    // 앱 사용 중 재설문이면 이전 main이 백스택에 남아 있으므로 함께 비운다
                    // (남아 있으면 뒤로가기로 돌아갔을 때 갱신 전 상태로 재설문이 다시 뜬다)
                    navController.navigate("main") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToInit = {
                    navController.navigate("init") {
                        popUpTo("InitQuestion") { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = "main",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            MainScreen(
                onNavigateToSetting = {
                    navController.navigate("setting") {
                        launchSingleTop = true
                    }
                },
                onNavigateToInitQuestion = {
                    navController.navigate("InitQuestion") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = "progress",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            CollectionScreen()
        }

        composable(
            route = "completed",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            FootprintsScreen(
                selectedTab = footprintsTab,
                onTabChange = { footprintsTab = it }
            )
        }

        composable(route = "setting") {
            SettingScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToMain = {
                    navController.navigate("init") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onNavigateToInitQuestion = {
                    navController.navigate("InitQuestion") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

    }
    }
}