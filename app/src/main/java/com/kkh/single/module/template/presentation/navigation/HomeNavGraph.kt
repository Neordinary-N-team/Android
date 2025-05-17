package com.kkh.single.module.template.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kkh.single.module.template.presentation.screen.home.MainScreen
import com.kkh.single.module.template.presentation.screen.home.MyPageScreen
import com.kkh.single.module.template.presentation.screen.selectdate.DailyRecordScreen

object HomeRoutes {
    const val MAIN = "MAIN"
    const val MENU = "MENU"
    const val MYPAGE = "MYPAGE"
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    onNavigateToSelectScreen: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoutes.MAIN
    ) {
        composable(HomeRoutes.MAIN) {
            MainScreen(onNavigateToSelectScreen = {
                onNavigateToSelectScreen()
            })
        }
        composable(HomeRoutes.MENU) {
            DailyRecordScreen()
        }
        composable(HomeRoutes.MYPAGE) {
            MyPageScreen()
        }
    }
}
