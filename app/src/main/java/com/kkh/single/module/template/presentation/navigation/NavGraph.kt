package com.kkh.single.module.template.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kkh.single.module.template.presentation.screen.home.HomeScreen
import com.kkh.single.module.template.presentation.screen.onboarding.CalendarScreen
import com.kkh.single.module.template.presentation.screen.onboarding.CanNotEatScreen
import com.kkh.single.module.template.presentation.screen.onboarding.InputUserInformationScreen
import com.kkh.single.module.template.presentation.screen.onboarding.RecommendMenuScreen
import com.kkh.single.module.template.presentation.screen.selectdate.DailyRecordScreen

object Routes {
    const val ONBOARDING = "onBoarding"
    const val INPUT_USER_INFORMATION = "inputUserInformation" // 달력
    const val RECOMMEND_MENU = "recommendMenu"
    const val CAN_NOT_EAT = "canNotEat"
    const val HOME = "home"
    const val SELECT_DATE = "SelectDate"
    const val CALENDAR = "CALENDAR"

}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startRoute: String = Routes.INPUT_USER_INFORMATION
) {
    NavHost(
        navController = navController,
        startDestination = startRoute
    ) {
//        composable(Routes.ONBOARDING) {
//            OnBoardingScreen(navController = navController)
//        }

        composable(Routes.INPUT_USER_INFORMATION) {
            InputUserInformationScreen(
                onNavigateToCalendar = {navController.navigate(Routes.CALENDAR)},
                onNavigateToRecommendMenu = {navController.navigate(Routes.RECOMMEND_MENU)}
            )
        }
        composable(
            route = "${Routes.INPUT_USER_INFORMATION}/{date}",
            arguments = listOf(navArgument("date") { type = NavType.StringType })
        ) { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date") ?: ""
            InputUserInformationScreen(
                date = date,
                onNavigateToCalendar = {navController.navigate(Routes.CALENDAR)},
                onNavigateToRecommendMenu = {navController.navigate(Routes.RECOMMEND_MENU)}
            )
        }

        composable(Routes.RECOMMEND_MENU) {
            RecommendMenuScreen(
                onNaviGateToCanNotEatFood = {
                    navController.navigate(Routes.CAN_NOT_EAT)
                }
            )
        }

        composable(Routes.CALENDAR) {
            CalendarScreen(onClickBack = {
                navController.navigate(Routes.INPUT_USER_INFORMATION)
            }, navigateToInputUserInfoScreen = { date ->
                navController.navigate("${Routes.INPUT_USER_INFORMATION}/{$date}")
            })
        }

        composable(Routes.CAN_NOT_EAT) {
            CanNotEatScreen(navigateToHome = {
                navController.navigate(Routes.HOME)
            })
        }

        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(Routes.SELECT_DATE) {
            DailyRecordScreen()
        }
    }
}