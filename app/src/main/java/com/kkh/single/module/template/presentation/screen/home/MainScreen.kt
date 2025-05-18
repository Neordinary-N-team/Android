package com.kkh.single.module.template.presentation.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.kkh.single.module.template.data.model.response.VeganMenu
import com.kkh.single.module.template.presentation.component.CustomCalendar
import com.kkh.single.module.template.presentation.screen.recipe.RecipeViewModel
import java.util.UUID

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreen(onNavigateToSelectScreen: () -> Unit = {}) {
    val sheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    val recipeViewModel: RecipeViewModel = hiltViewModel()

    var isSwitched by remember { mutableStateOf(false) }
    var selectedDateState by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("나는 지금 임신 4주차") }


    LaunchedEffect(isSwitched) {
        if (isSwitched){
            sheetState.bottomSheetState.partialExpand()
            text = "추천되었던 식단 기록이에요!"
        }
        else{
            sheetState.bottomSheetState.expand()
            text = "나는 지금 임신 4주차"
        }
    }

    LaunchedEffect(selectedDateState) {
        sheetState.bottomSheetState.expand()
    }

    LaunchedEffect(Unit) {
        recipeViewModel.requestCreateVeganMenu()
        recipeViewModel.getVeganMenus(
            date = "2025-05-20"
        )
    }

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetDragHandle = {},
        topBar = {},
        sheetPeekHeight = 100.dp, // 'Little' 상태의 높이
        sheetContent = {
            Box(Modifier.fillMaxHeight(0.8f)) {
                BottomSheetContent(recipeViewModel.allowedVeganFoods.value,text)
            }

        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) { paddingValues ->
        // 기존 MainScreen UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NeodinaryColors.White.White)
                .padding(paddingValues)
        ) {
            MainTopBar(isSwitchOn = isSwitched, onSwitchChange = { isSwitched = it })

            if (!isSwitched) {
                val dateList = listOf(
                    "월 19일", "화 20일", "수 21일", "목 22일", "금 23일", "토 24일", "일 25일"
                )

                Spacer(Modifier.height(10.dp))

                DateRow(
                    dateList = dateList,
                    selectedIndex = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(NeodinaryColors.Gray.WGray800)
                )
//
//            Spacer(Modifier.height(25.dp))
//            MenuColumn(modifier = Modifier.padding(horizontal = 25.dp))
            } else {
                AndroidView(
                    factory = { context ->
                        CustomCalendar(context).apply {
                            // 달력에서 날짜 선택 시 "2025년 5월" 형태 문자열 리턴
                            setOnDateSelectedListener { formattedDate ->
                                Log.e("test", "## [달력] formattedDate : $formattedDate")
                                selectedDateState = formattedDate
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                )
            }
        }


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
fun BottomSheetContent(list : List<VeganMenu>, text : String) {
    val dummyVeganMenus = listOf(
        VeganMenu(
            id = 1L,
            date = "2025-05-17",
            name = "채소 볶음밥",
            mealType = "아침",
            image ="data:image/jpeg;base64,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",
            calories = 450,
            difficulty = "쉬움",
            time = "20분"
        ),
        VeganMenu(
            id = 2L,
            date = "2025-05-17",
            name = "두부 샐러드",
            mealType = "점심",
            image = "data:image/jpeg;base64,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",
            calories = 350,
            difficulty = "중간",
            time = "15분"
        ),
        VeganMenu(
            id = 3L,
            date = "2025-05-18",
            name = "과일 스무디",
            mealType = "저녁",
            image = "data:image/jpeg;base64,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",
            calories = 200,
            difficulty = "쉬움",
            time = "10분"
        )
    )
    Column(
        Modifier
            .background(NeodinaryColors.White.White)
            .fillMaxSize()
            .padding(horizontal = 18.dp, vertical = 23.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = text,
            style = NeodinaryTypography.Caption_Medium
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = "필요한 영양소에 맞춘\n" +
                    "주간 식단을 추천해 드릴게요.",
            style = NeodinaryTypography.HeadLine2_SemiBold
        )
        dummyVeganMenus.forEach { menu ->
            Spacer(Modifier.height(12.dp))
            CardViewItem(menu)
        }

    }

}

@Preview
@Composable
fun CardViewItem(veganMenu: VeganMenu? = null) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(321.dp)
            .background(NeodinaryColors.White.White),
        shape = RoundedCornerShape(10.dp), shadowElevation = 15.dp
    ) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyRow {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFF47DB111A))
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        Text(
                            veganMenu?.mealType ?: "점심",
                            style = NeodinaryTypography.Caption_Medium,
                            color = NeodinaryColors.Green.Green400
                        )
                    }
                    Spacer(Modifier.width(4.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFF47DB111A))
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        Text(
                            text = "${veganMenu?.calories}kcal",
                            style = NeodinaryTypography.Caption_Medium,
                            color = NeodinaryColors.Green.Green400
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "식단명",
                style = NeodinaryTypography.Subtitle1_SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Row {
                Text(
                    text = "조리시간",
                    style = NeodinaryTypography.Body2_Regular,
                    color = NeodinaryColors.Gray.WGray600
                )
                Spacer(Modifier.width(4.dp))

                Text(
                    text = veganMenu?.time ?: "10분",
                    style = NeodinaryTypography.Body2_Regular,
                    color = NeodinaryColors.Green.Green500
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "난이도",
                    style = NeodinaryTypography.Body2_Regular,
                    color = NeodinaryColors.Gray.WGray600
                )
                Spacer(Modifier.width(4.dp))

                Text(
                    text = veganMenu?.difficulty?: "중",
                    style = NeodinaryTypography.Body2_Regular,
                    color = NeodinaryColors.Green.Green500
                )
            }
            Spacer(Modifier.height(12.dp))
            AsyncImage(
                model = veganMenu?.image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(195.dp)
            )
        }
    }
}


@Composable
fun MainTopBar(isSwitchOn: Boolean, onSwitchChange: (Boolean) -> Unit) {
    TopAppBar(
        backgroundColor = NeodinaryColors.White.White,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp),
        title = {
            Text("식단 추천", style = Headline1_Bold, color = NeodinaryColors.Black.Black)
        }, actions = {
            Switch(
                checked = isSwitchOn,
                onCheckedChange = { onSwitchChange(it) },
                thumbContent = {
                    // 내부 동그라미 커스터마이징
                    Icon(
                        painter = if (isSwitchOn) painterResource(R.drawable.ic_home_face) else painterResource(
                            R.drawable.ic_home_main
                        ),
                        contentDescription = null,
                        modifier = Modifier.padding(4.dp),
                        tint = if (isSwitchOn) Color.Blue else Color.Gray
                    )
                    // 비활성화 상태에서 추가 아이콘 표시
//                    if (!스위치_상태) {
//                        Icon(
//                            painter = painterResource(R.drawable.ic_home_face), // 비활성화 상태에 추가할 아이콘
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(24.dp) // 추가 아이콘 크기 조정
//                                .offset(x = 8.dp, y = (-8).dp), // 위치 조정 (오른쪽 위로 이동 예시)
//                            tint = Color.Red // 추가 아이콘 색상
//                        )
//                    }
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

    Box(
        modifier = Modifier
            .size(44.dp, 80.dp)
            .clip(RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                dayLabel,
                style = NeodinaryTypography.Subtitle1_Regular,
                color = NeodinaryColors.White.White,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(16.dp))
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = if (isSelected) NeodinaryColors.Green.Green300 else Color.Unspecified
            ) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        dateLabel,
                        style = NeodinaryTypography.Body1_Medium,
                        color = NeodinaryColors.White.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DateRow(
    dateList: List<String>,
    selectedIndex: Int = 0,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.height(100.dp)
    ) {
        dateList.forEachIndexed { index, fullDate ->
            val parts = fullDate.split(" ") // ex: "월 19일"
            val day = parts.getOrNull(0) ?: ""
            val date = parts.getOrNull(1) ?: ""

            DateItem(
                dayLabel = day,
                dateLabel = date,
                isSelected = index == selectedIndex
            )
            Spacer(modifier = Modifier.width(8.dp))
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