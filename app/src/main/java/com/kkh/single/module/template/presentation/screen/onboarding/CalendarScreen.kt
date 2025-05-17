package com.kkh.single.module.template.presentation.screen.onboarding

import android.util.Log
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.component.CustomBottomButton
import com.kkh.single.module.template.presentation.component.CustomCalendar
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography

@Composable
fun CalendarScreen(
    onClickBack: () -> Unit,
    navigateToInputUserInfoScreen: (String) -> Unit
){

    var selectedDateState by remember { mutableStateOf("") }

    Scaffold(topBar = {CalendarTopBar{onClickBack()}}) { innerPadding ->
        Column(Modifier.padding(innerPadding)){
            AndroidView(
                factory = { context ->
                    CustomCalendar(context).apply {
                        // 달력에서 날짜 선택 시 "2025년 5월" 형태 문자열 리턴
                        setOnDateSelectedListener { formattedDate ->
                            val text = getSelectedDateFormatted()
                            Log.e("test", "## [달력] formattedDate : $formattedDate")
                            selectedDateState = text
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )

            // Spacer로 나머지 공간을 채워 버튼을 하단으로 밀어냄
            Spacer(modifier = Modifier.weight(1f))

            CustomBottomButton(
                enable = selectedDateState.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp), // 적절한 패딩 추가
                onClickButton = {
                    navigateToInputUserInfoScreen(selectedDateState)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTopBar(onClickBack: () -> Unit) {
    Box(Modifier
        .fillMaxWidth()
        .height(120.dp)) {
        IconButton(onClick = { onClickBack() }, Modifier.size(24.dp)) {
            Icon(
                painter = painterResource(R.drawable.ic_onboarding_vomiting),
                contentDescription = ""
            )
        }
        Text(
            "임신 날짜 선택",
            color = NeodinaryColors.Black.Black,
            style = NeodinaryTypography.Subtitle1_SemiBold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
