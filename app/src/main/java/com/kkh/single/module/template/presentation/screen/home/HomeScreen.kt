package com.kkh.single.module.template.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kkh.single.module.template.presentation.navigation.BottomNavItem
import com.kkh.single.module.template.presentation.navigation.HomeNavGraph
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryColors.Gray

@Composable
fun HomeScreen(mainNavController: NavHostController) {

    val homeNavController = rememberNavController()

    Scaffold(
        topBar = { Spacer(Modifier.height(47.dp)) },
        bottomBar = {
            HomeBottomNavigation(homeNavController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            HomeNavGraph(
                navController = homeNavController,
                onNavigateToSelectScreen = {
                    mainNavController.navigate(Routes.SELECT_DATE)
                }
            )
        }
    }
}

@Composable
fun HomeBottomNavigation(navController: NavHostController) {

    val items = listOf<BottomNavItem>(
        BottomNavItem.Calendar,
        BottomNavItem.Timeline,
        BottomNavItem.Analysis,
    )

    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier.height(58.dp),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        modifier = Modifier.size(30.dp),
                        contentDescription = "",
                        tint = if (currentRoute == item.screenRoute) {
                            NeodinaryColors.Green.Green300
                        } else NeodinaryColors.Gray.WGray800
                    )
                },
                selectedContentColor = NeodinaryColors.Black.Black,
                unselectedContentColor = Gray.WGray100,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}