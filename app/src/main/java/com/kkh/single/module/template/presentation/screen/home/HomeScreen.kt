package com.kkh.single.module.template.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryColors.Gray

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object Calendar : BottomNavItem("title_calendar", R.drawable.ic_launcher_background, "CALENDAR")
    object Timeline : BottomNavItem("title_calendar", R.drawable.ic_launcher_background, "TIMELINE")
    object Analysis : BottomNavItem("title_calendar", R.drawable.ic_launcher_background, "ANALYSIS")
    object Settings : BottomNavItem("title_calendar", R.drawable.ic_launcher_background, "SETTINGS")
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(bottomBar = {
        NeodinaryBottomNavigation(navController)
    }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("hi")
        }
    }
}

@Composable
fun NeodinaryBottomNavigation(navController: NavHostController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Calendar,
        BottomNavItem.Timeline,
        BottomNavItem.Analysis,
        BottomNavItem.Settings
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon), modifier = Modifier
                            .width(26.dp)
                            .height(26.dp), contentDescription = ""
                    )
                },
                label = { Text(item.title, fontSize = 9.sp) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Gray.WGray100,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
//                    navController.navigate(item.screenRoute) {
//                        navController.graph.startDestinationRoute?.let {
//                            popUpTo(it) { saveState = true }
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
                }
            )
        }
    }
}