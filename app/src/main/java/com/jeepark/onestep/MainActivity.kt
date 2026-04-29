package com.jeepark.onestep

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.jeepark.onestep.util.LocationHelper
import com.jeepark.onestep.util.NotificationHelper
import com.jeepark.onestep.ui.screens.AuthScreen
import com.jeepark.onestep.ui.screens.CompletedQuestsScreen
import com.jeepark.onestep.ui.screens.InitQuestionScreen
import com.jeepark.onestep.ui.screens.InitScreen
import com.jeepark.onestep.ui.screens.MainScreen
import com.jeepark.onestep.ui.screens.CollectionScreen
import com.jeepark.onestep.ui.screens.SettingScreen
import com.jeepark.onestep.ui.screens.SignupScreen
import com.jeepark.onestep.ui.theme.OneStepTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        NotificationHelper.createChannel(this)
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

    val prefs = context.getSharedPreferences(NotificationHelper.PREFS_NAME, android.content.Context.MODE_PRIVATE)

    val notifPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted && prefs.getBoolean(NotificationHelper.KEY_NOTIF, true)) {
            NotificationHelper.schedule(context)
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) LocationHelper.updateLocation(context) {}
    }

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notifPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else if (prefs.getBoolean(NotificationHelper.KEY_NOTIF, true)) {
            NotificationHelper.schedule(context)
        }
    }

    NavHost(navController = navController, startDestination = "init") {

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
                onNavigateToSignup = {
                    navController.navigate("signup") {
                        popUpTo("auth") { inclusive = true }
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
                    navController.navigate("main") {
                        popUpTo("InitQuestion") { inclusive = true }
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
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None }
        ) {
            MainScreen(
                onNavigateToProgress = {
                    navController.navigate("progress") {
                        launchSingleTop = true
                    }
                },
                onNavigateToCompleted = {
                    navController.navigate("completed") {
                        launchSingleTop = true
                    }
                },
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
            popExitTransition = { ExitTransition.None }
        ) {
            CollectionScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "completed",
            enterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            CompletedQuestsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
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