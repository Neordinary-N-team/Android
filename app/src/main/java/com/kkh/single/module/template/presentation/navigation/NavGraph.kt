package com.kkh.single.module.template.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kkh.single.module.template.presentation.screen.home.HomeScreen
import com.kkh.single.module.template.presentation.screen.onboarding.CanNotEatScreen
import com.kkh.single.module.template.presentation.screen.onboarding.InputUserInformationScreen
import com.kkh.single.module.template.presentation.screen.onboarding.OnBoardingScreen
import com.kkh.single.module.template.presentation.screen.onboarding.RecommendMenuScreen
import com.kkh.single.module.template.presentation.screen.selectdate.SelectDateScreen
import okhttp3.Route

object Routes {
    const val ONBOARDING = "onBoarding"
    const val INPUT_USER_INFORMATION = "inputUserInformation" // 달력
    const val RECOMMEND_MENU = "recommendMenu"
    const val CAN_NOT_EAT = "canNotEat"
    const val HOME = "home"
    const val SELECT_DATE = "SelectDate"
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startRoute: String = Routes.ONBOARDING
) {
    NavHost(
        navController = navController,
        startDestination = startRoute
    ) {
        composable(Routes.ONBOARDING) {
            OnBoardingScreen(navController = navController)
        }

        composable(Routes.INPUT_USER_INFORMATION) {
            InputUserInformationScreen(
                navController = navController,
                onNavigateToCalendar = {}
            )
        }

        composable(Routes.RECOMMEND_MENU) {
            RecommendMenuScreen(
                navController = navController,
                onNavigateToCalendar = {}
            )
        }

        composable(Routes.CAN_NOT_EAT) {
            CanNotEatScreen(navController)
        }

        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(Routes.SELECT_DATE) {
            SelectDateScreen()
        }
    }
}