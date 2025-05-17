package com.kkh.single.module.template.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kkh.single.module.template.presentation.screen.home.FirstScreen
import com.kkh.single.module.template.presentation.screen.home.HomeScreen
import com.kkh.single.module.template.presentation.screen.home.ThirdScreen
import com.kkh.single.module.template.presentation.screen.selectdate.SelectDateScreen

object HomeRoutes {
    const val First = "First"
    const val SelectDate = "SelectDate"
    const val Third = "Third"
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    onNavigateToSelectScreen: () -> Unit = {}
) {

    NavHost(
        navController = navController,
        startDestination = HomeRoutes.First
    ) {
        composable(HomeRoutes.First) {
            FirstScreen(onNavigateToSelectScreen = {
                onNavigateToSelectScreen()
            })
        }
        composable(HomeRoutes.SelectDate) {
            SelectDateScreen()
        }
        composable(HomeRoutes.Third) {
            ThirdScreen()
        }
    }
}
