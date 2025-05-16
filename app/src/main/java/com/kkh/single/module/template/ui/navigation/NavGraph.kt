package com.kkh.single.module.template.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

object Routes {
    const val HOME = "home"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
//            HomeScreen(
//                onNavigateToDetail = {
//                    navController.navigate(Routes.DETAIL)
//                }
//            )
        }
    }
}
