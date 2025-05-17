package com.kkh.single.module.template.presentation.screen.home

import android.util.Log
import android.view.WindowInsets
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.navigation.HomeNavGraph
import com.kkh.single.module.template.presentation.navigation.HomeRoutes
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryColors.Gray
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.Body2_Regular
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.HeadLine2_SemiBold
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.Headline1_Bold

sealed class BottomNavItem(
    val title: String, val icon: Int, val screenRoute: String
) {
    object Calendar :
        BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, HomeRoutes.First)

    object Timeline :
        BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, HomeRoutes.SelectDate)

    object Analysis :
        BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, HomeRoutes.Third)

    object Settings :
        BottomNavItem("title_calendar", R.drawable.ic_launcher_foreground, HomeRoutes.First)
}

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

@Preview
@Composable
fun FirstScreen(onNavigateToSelectScreen : () -> Unit = {}){

    Column {
        TopText()
        Spacer(Modifier.height(24.dp))
        DateText(onClick = {
            onNavigateToSelectScreen()
        })

        val dateList = listOf(
            "월 19일", "화 20일", "수 21일", "목 22일", "금 23일", "토 24일", "일 25일"
        )
        Spacer(Modifier.height(10.dp))

        DateRow(
            dateList = dateList,
            selectedIndex = 2,
            modifier = Modifier.padding(horizontal = 25.dp)
        )

        Spacer(Modifier.height(25.dp))
        MenuColumn(modifier = Modifier.padding(horizontal = 25.dp))
    }
}

@Composable
fun ThirdScreen() {
    Text("third")
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
fun DateText(onClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {
        Spacer(Modifier.width(21.dp))
        Text(
            text = "< 5월 19일 - 25일 >",
            style = Body2_Regular,
            fontSize = 13.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDateRow() {
    // 예시용 날짜 리스트
    val dateList = listOf(
        "월 19일", "화 20일", "수 21일", "목 22일", "금 23일", "토 24일", "일 25일"
    )
    DateRow(dateList = dateList, selectedIndex = 2) // 예시로 3번째 선택됨
}

@Composable
fun DateItem(dayLabel: String, dateLabel: String, isSelected: Boolean) {
    val borderModifier = if (isSelected) {
        Modifier.border(1.dp, Color.Black, RoundedCornerShape(8.dp))
    } else {
        Modifier
    }

    Box(
        modifier = Modifier
            .size(44.dp, 39.dp)
            .clip(RoundedCornerShape(5.dp))
            .then(borderModifier),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                dayLabel,
                style = NeodinaryTypography.Subtitle1_Regular,
                fontSize = 10.sp
            )
            Text(
                dateLabel,
                style = NeodinaryTypography.Subtitle1_Regular,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DateRow(
    dateList: List<String>,
    selectedIndex: Int = 0,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        dateList.forEachIndexed { index, fullDate ->
            val parts = fullDate.split(" ") // ex: "월 19일"
            val day = parts.getOrNull(0) ?: ""
            val date = parts.getOrNull(1) ?: ""

            DateItem(
                dayLabel = day,
                dateLabel = date,
                isSelected = index == selectedIndex
            )
        }
    }
}

@Preview
@Composable
fun MenuItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(206.dp)
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(horizontal = 15.dp)
    ) {
        Spacer(Modifier.height(22.dp))
        Text("오늘의 아침", style = HeadLine2_SemiBold, fontSize = 16.sp)
        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("dd")
            Text("dd")
            Text("dd")
            Text("dd")
        }
        Spacer(Modifier.height(21.dp))

        LoadImageFromUrl(
            "https://images.unsplash.com/photo-1506744038136-46273834b3fb\n",
            modifier = Modifier.height(99.dp)
        )

    }
}

@Composable
fun MenuColumn(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(count = 10) {
            MenuItem()
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun LoadImageFromUrl(url: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
    )
}


@Composable
fun HomeBottomNavigation(navController: NavHostController) {

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