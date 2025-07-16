package com.kmpstudy

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kmpstudy.ui.page.MainPage
import com.kmpstudy.ui.page.SearchPage
import com.kmpstudy.di.di
import com.mywf.ui.theme.AppTheme
import org.kodein.di.compose.withDI

val LocalDrawerState = compositionLocalOf<DrawerState> { error("No DrawerState provided") }
val LocalNavController = compositionLocalOf<NavController> { error("Not provided") }

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun App() = withDI(di) {
    AppTheme {
        SharedTransitionLayout {
            val navController = rememberNavController()
            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalDrawerState provides DrawerState(initialValue = DrawerValue.Closed)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "MainPage"
                ) {
                    composable(
                        "MainPage",
                        exitTransition = {
                            fadeOut()
                        },
                        popEnterTransition = {
                            fadeIn()
                        }
                    ) {
                        MainPage(
                            this@SharedTransitionLayout,
                            this@composable
                        )
                    }
                    composable(
                        "SearchPage",
                        enterTransition = {
                            fadeIn()
                        },
                        exitTransition = {
                            fadeOut()
                        },
                        popExitTransition = {
                            fadeOut()
                        }
                    ) {
                        SearchPage(
                            this@SharedTransitionLayout,
                            this@composable
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AndroidApp() {
    App()
}

@Composable
fun IosApp() {
    App()
}

@Composable
fun DesktopApp() {
    App()
}


@Composable
fun WebApp() {
    App()
}