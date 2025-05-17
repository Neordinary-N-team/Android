package com.kkh.single.module.template.presentation.screen.home

import android.util.Log
import android.view.WindowInsets
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryColors.Gray
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.Body2_Regular
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.HeadLine2_SemiBold
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.Headline1_Bold

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object Calendar : BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, "CALENDAR")
    object Timeline : BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, "TIMELINE")
    object Analysis : BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, "ANALYSIS")
    object Settings : BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, "SETTINGS")
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { Spacer(Modifier.height(47.dp)) },
        bottomBar = {
            NeodinaryBottomNavigation(navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TopText()

            Spacer(Modifier.height(24.dp))

            DateText()


        }
    }
}

@Composable
fun TopText() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.width(21.dp))
        Text(
            text = "필요한 영양소에 맞춘\n" +
                    "주간 식단을 추천해 드릴게요.",
            style = Headline1_Bold
        )
    }
}

@Composable
fun DateText() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.width(21.dp))
        Text(
            text = "< 5월 19일 - 25일 >",
            style = Body2_Regular,
            fontSize = 13.sp
        )
    }
}

@Composable
fun DateItem() {
    Box(
        modifier = Modifier
            .size(44.dp, 39.dp)
            .clip(RoundedCornerShape(5.dp)) // 먼저 clip
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 그리고 border
        , contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("월")
            Text("5월 19일")
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
        modifier = Modifier.navigationBarsPadding(),
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
                            .width(24.dp)
                            .height(24.dp), contentDescription = ""
                    )
                },
                label = { Text(item.title, fontSize = 9.sp) },
                selectedContentColor = NeodinaryColors.Black.Black,
                unselectedContentColor = Gray.WGray100,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {
                    Log.d("BottomNav", "Clicked on ${item.title}")
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