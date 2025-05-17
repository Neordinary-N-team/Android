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
        list.forEach { menu ->
        }
        Spacer(Modifier.height(12.dp))
        CardViewItem()
        Spacer(Modifier.height(12.dp))
        CardViewItem()
        Spacer(Modifier.height(12.dp))
        CardViewItem()

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
                            text = veganMenu?.calories.toString(),
                            style = NeodinaryTypography.Caption_Medium,
                            color = NeodinaryColors.Green.Green400
                        )
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "필요한 영양소에 맞춘",
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
                model = "",
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