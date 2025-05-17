package com.kkh.single.module.template.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.component.NeodinaryTopBar
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.Body2_Regular
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.HeadLine2_SemiBold
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.Headline1_Bold


@Preview
@Composable
fun MainScreen(onNavigateToSelectScreen: () -> Unit = {}) {

    Column(modifier = Modifier.background(NeodinaryColors.White.White)) {
//        NeodinaryTopBar(
//            onClickMainIcon = {},
//            onClickAddIcon = {},
//            titleImgResource = R.drawable.ic_home_main,
//            actionsImgResource = R.drawable.ic_home_add
//        )
        MainTopBar()
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
    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 17.dp, end = 18.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        WriteDiaryFloatingButton()
    }
}

@Composable
fun MainTopBar(){
    var 스위치_상태 by remember { mutableStateOf(false) }
    TopAppBar(
        backgroundColor = NeodinaryColors.White.White,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp),
        title = {
            Text("식단 추천",style = Headline1_Bold, color = NeodinaryColors.Black.Black)
        }, actions = {
            Switch(
                checked = 스위치_상태,
                onCheckedChange = { 스위치_상태 = it },
                thumbContent = {
                    // 내부 동그라미 커스터마이징
                    Icon(
                        painter = if (스위치_상태) painterResource(R.drawable.ic_home_face) else painterResource(R.drawable.ic_home_main),
                        contentDescription = null,
                        modifier = Modifier.padding(4.dp),
                        tint = if (스위치_상태) Color.Blue else Color.Gray
                    )
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xFF2196F3), // 켜짐 상태의 동그라미 색상
                    uncheckedThumbColor = Color.White, // 꺼짐 상태의 동그라미 색상
                    checkedTrackColor = Color(0xFF64B5F6), // 켜짐 상태의 트랙 색상
                    uncheckedTrackColor = Color(0xFFB0BEC5) // 꺼짐 상태의 트랙 색상
                )
            )
        })
}

@Composable
fun WriteDiaryFloatingButton() {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = NeodinaryColors.Black.Black
        ),
        shape = RoundedCornerShape(32.dp),
        onClick = {

        },
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_home_face),
            modifier = Modifier.size(24.dp),
            contentDescription = "",
            tint = NeodinaryColors.White.White
        )
        Spacer(Modifier.width(2.dp))
        Text(
            "일지 쓰기",
            color = NeodinaryColors.White.White,
            style = NeodinaryTypography.Body2_Medium
        )
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
fun DateText(onClick: () -> Unit) {
    Row(
        modifier = Modifier
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