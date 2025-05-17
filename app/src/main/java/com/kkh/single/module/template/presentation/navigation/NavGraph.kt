package com.kkh.single.module.template.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kkh.single.module.template.presentation.screen.home.HomeScreen
import com.kkh.single.module.template.presentation.screen.selectdate.SelectDateScreen
import okhttp3.Route

object Routes {
    const val HOME = "home"
    const val SELECT_DATE = "SelectDate"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(Routes.SELECT_DATE) {
            SelectDateScreen()
        }
    }
}
