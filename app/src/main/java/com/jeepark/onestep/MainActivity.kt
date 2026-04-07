package com.jeepark.onestep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.jeepark.onestep.ui.screens.AuthScreen
import com.jeepark.onestep.ui.screens.InitQuestionScreen
import com.jeepark.onestep.ui.screens.InitScreen
import com.jeepark.onestep.ui.screens.MainQuestScreen
import com.jeepark.onestep.ui.screens.MainScreen
import com.jeepark.onestep.ui.screens.CollectionScreen
import com.jeepark.onestep.ui.screens.ReceiveQuestScreen
import com.jeepark.onestep.ui.screens.SettingScreen
import com.jeepark.onestep.ui.screens.SignupScreen
import com.jeepark.onestep.ui.theme.OneStepTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
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
    val navController = rememberNavController()

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
                onNavigateToMainQuest = {
                    navController.navigate("ReceiveQuest") {
                        launchSingleTop = true
                    }
                },
                onNavigateToProgress = {
                    navController.navigate("progress") {
                        launchSingleTop = true
                    }
                },
                onNavigateToSetting = {
                    navController.navigate("setting") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(route = "ReceiveQuest") {
            ReceiveQuestScreen(
                onNavigateToQuest = {
                    navController.navigate("mainQuest") {
                        popUpTo("ReceiveQuest") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "mainQuest") {
            MainQuestScreen(
                onNavigateToMain = {
                    navController.navigate("main") {
                        popUpTo("mainQuest") { inclusive = true }
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