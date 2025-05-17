package com.kkh.single.module.template.presentation.screen.selectdate

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.component.CustomCalendarWithPhoto
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTheme
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography.Headline1_Bold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyRecordScreen() {
    val sheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedDateText by remember { mutableStateOf("") }

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetDragHandle = {},
        topBar = {},
        sheetPeekHeight = 0.dp, // 처음엔 안 보이게
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                DailyRecordBottomSheetContent(
                    topText = selectedDateText
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .background(color = NeodinaryColors.White.White)
                .padding(innerPadding)
        ) {
            TopBar()
            Spacer(modifier = Modifier.height(10.dp))
            CalendarWithPhoto(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                onDateSelected = { date ->
                    selectedDateText = "$date 식단 일지"
                    scope.launch {
                        sheetState.bottomSheetState.expand()
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            TodayChaeOnUp()
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
}

@Composable
fun TopBar() {
    TopAppBar(
        backgroundColor = NeodinaryColors.White.White,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp),
        title = {
            Text(
                text = "일지",
                style = Headline1_Bold,
                color = NeodinaryColors.Black.Black
            )
        },
    )
}

@Composable
fun DailyRecordBottomSheetContent(
    modifier: Modifier = Modifier,
    topText: String,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(start = 17.dp, end = 17.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(23.dp))
        Text(
            style = NeodinaryTypography.HeadLine2_SemiBold,
            text = topText
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .size(
                    width = 114.dp,
                    height = 40.dp
                )
                .background(
                    color = Color(0xFF47DB11).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(
                    horizontal = 13.dp,
                    vertical = 10.dp
                ),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "아침",
                color = Color(0xFF2BAB08),
                style = NeodinaryTypography.Body2_Medium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier
                .size(
                    width = 359.dp,
                    height = 168.dp,
                )
                .fillMaxWidth(),
            painter = painterResource(R.drawable.ic_onboarding_calendar),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 17.dp,
                    end = 17.dp
                )
                .background(
                    color = Color(0xFFF4F4F4),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Text(
                text = "test",
                style = NeodinaryTypography.Body2_Medium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .size(
                    width = 114.dp,
                    height = 40.dp
                )
                .background(
                    color = Color(0xFF3078FF).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(
                    horizontal = 13.dp,
                    vertical = 10.dp
                ),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "점심",
                color = Color(0xFF3078FF),
                style = NeodinaryTypography.Body2_Medium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier
                .size(
                    width = 359.dp,
                    height = 168.dp,
                )
                .fillMaxWidth(),
            painter = painterResource(R.drawable.ic_onboarding_calendar),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 17.dp,
                    end = 17.dp
                )
                .background(
                    color = Color(0xFFF4F4F4),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Text(
                text = "test2",
                style = NeodinaryTypography.Body2_Medium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .size(
                    width = 114.dp,
                    height = 40.dp
                )
                .background(
                    color = Color(0xFFFF4545).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(
                    horizontal = 13.dp,
                    vertical = 10.dp
                ),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "저녁",
                color = Color(0xFFFF4545),
                style = NeodinaryTypography.Body2_Medium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            modifier = Modifier
                .size(
                    width = 359.dp,
                    height = 168.dp,
                )
                .fillMaxWidth(),
            painter = painterResource(R.drawable.ic_onboarding_calendar),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 17.dp,
                    end = 17.dp
                )
                .background(
                    color = Color(0xFFF4F4F4),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Text(
                text = "test3",
                style = NeodinaryTypography.Body2_Medium,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun CalendarWithPhoto(
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit = { _ -> }
) = AndroidView(
        factory = { context ->
            CustomCalendarWithPhoto(context).apply {
                setOnDateSelectedListener {
                    val date = getFormattedSelectedDateWithDay()
                    onDateSelected(date)
                }
            }
        },
        modifier = modifier
    )

@Composable
fun TodayChaeOnUp(modifier: Modifier = Modifier) =
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp)
            .background(
                color = NeodinaryColors.Gray.WGray100,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFEFEFEF),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 14.dp, top = 16.dp)
                .background(
                    color = NeodinaryColors.Gray.WGray100,
                ),
            text = "오늘의 채온 상승",
            style = NeodinaryTypography.Subtitle1_SemiBold,
        )
        MorningChipArea()
        LaunchChipArea()
        EveningChipArea()
        Spacer(modifier = Modifier.height(16.dp))
    }

@Composable
private fun LaunchChipArea() {
    Row(
        modifier = Modifier
            .padding(start = 14.dp)
            .background(
                color = NeodinaryColors.Gray.WGray100,
                shape = RoundedCornerShape(40.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RoundedChip(
            backgroundColor = Color(0xFF3078FF).copy(alpha = 0.1f)
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
                text = "점심",
                color = Color(0xFF3078FF)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "단백질을 잘 챙기셨어요! 엽산은 더 신경 써볼까요?",
            style = NeodinaryTypography.Body2_Regular,
        )
    }
}

@Composable
private fun MorningChipArea() {
    Row(
        modifier = Modifier
            .padding(start = 14.dp)
            .background(
                color = NeodinaryColors.Gray.WGray100,
                shape = RoundedCornerShape(40.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RoundedChip(
            backgroundColor = Color(0xFF47DB11).copy(alpha = 0.1f)
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
                text = "아침",
                color = Color(0xFF2BAB08)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "철분이 듬뿍! 다만 칼슘이 더 필요해 보여요.",
            style = NeodinaryTypography.Body2_Regular,
        )
    }
}

@Composable
private fun EveningChipArea() {
    Row(
        modifier = Modifier
            .padding(start = 14.dp)
            .background(
                color = NeodinaryColors.Gray.WGray100,
                shape = RoundedCornerShape(40.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RoundedChip(
            backgroundColor = Color(0xFFFF4545).copy(alpha = 0.1f)
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
                text = "저녁",
                color = Color(0xFFFF4545)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "철분을 잘 섭취했어요. 엽산은 살짝 부족했네요.",
            style = NeodinaryTypography.Body2_Regular,
        )
    }
}

@Composable
fun RoundedChip(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    content: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(40.dp)
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
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

@Preview
@Composable
private fun DailyRecordScreenPreview() {
    NeodinaryTheme {
        DailyRecordScreen()
    }
}