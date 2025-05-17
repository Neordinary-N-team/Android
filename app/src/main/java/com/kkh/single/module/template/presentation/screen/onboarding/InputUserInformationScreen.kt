package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.component.ChipTitle
import com.kkh.single.module.template.presentation.component.CustomBottomButton
import com.kkh.single.module.template.presentation.component.CustomChip
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.screen.selectdate.SelectDateTopBar
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTheme
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography


/**
 * 임신 날짜, 키, 몸무게, 나이 선택 화면
 */
@Composable
fun InputUserInformationScreen(
    date: String = "날짜 선택하기",
    onNavigateToCalendar: () -> Unit,
    onNavigateToRecommendMenu: () -> Unit
) {

    var selectedIndex by remember { mutableIntStateOf(-1) }
    var selectedIndex2 by remember { mutableIntStateOf(-1) }
    var selectedIndex3 by remember { mutableIntStateOf(-1) }

    val list = listOf(
        "40kg 이하",
        "41kg~45kg",
        "46kg~50kg",
        "56kg~60kg",
        "61kg~65kg",
        "66kg~70kg",
        "71kg~75kg",
        "76kg~80kg",
        "81kg~85kg",
        "86kg~90 kg",
        "91kg~95kg",
        "96kg~100kg",
        "101kg 이상"
    )
    val list2 = listOf(
        "150cm 이하",
        "151cm~155cm",
        "156cm~160cm",
        "161cm~165cm",
        "166cm~170cm",
        "171cm~175cm",
        "176~180cm",
        "181cm 이상"
    )
    val list3 = listOf(
        "20세 이하",
        "21세~25세",
        "26세~30세",
        "36세~40세",
        "41세~45세",
        "46세~50세",
        "50세 이상"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Spacer(Modifier.height(24.dp))
        }, bottomBar = {
            CustomBottomButton(
                text = "다음",
                enable = selectedIndex != -1 && selectedIndex2 != -1 && selectedIndex3 != -1,
                onClickButton = { onNavigateToRecommendMenu() })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "1/3 단계",
                style = NeodinaryTypography.Caption_Medium,
                color = NeodinaryColors.Green.Green400
            )
            Text(
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        start = 16.dp
                    ),
                text = "간단한 정보를 입력해 주시면\n맞춤형 식단을 추천해 드릴게요.",
                style = NeodinaryTypography.HeadLine2_SemiBold,
                color = NeodinaryColors.Black.Black,
            )

            Spacer(Modifier.height(26.dp))

            DatePregnantBar(date = date, onClickCalendar = {
                onNavigateToCalendar()
            })

            SelectInfoItem(
                text = "이전 임신 여부",
                selectedIndex = selectedIndex,
                onChipSelected = { selectedIndex = it },
                list = list
            )
            SelectInfoItem(
                text = "몸무게",
                selectedIndex = selectedIndex2,
                onChipSelected = { selectedIndex2 = it },
                list = list2
            )
            SelectInfoItem(
                text = "나이",
                selectedIndex = selectedIndex3,
                onChipSelected = { selectedIndex3 = it },
                list = list3
            )

        }
    }
}


@Composable
fun OnboardingText(text: String) {
    Text(
        text,
        style = NeodinaryTypography.Caption_Medium,
        color = NeodinaryColors.Gray.WGray600
    )
}

@Composable
fun SelectInfoItem(
    text: String = "key",
    selectedIndex: Int,
    onChipSelected: (Int) -> Unit,
    list: List<String>
) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(119.dp)
            .padding(horizontal = 18.dp)
    ) {
        HorizontalDivider(thickness = 2.dp, color = NeodinaryColors.Gray.WGray100)
        OnboardingText(text)
        Spacer(Modifier.height(12.dp))
        LazyRow {
            itemsIndexed(list) { index, item ->
                val isSelected = index == selectedIndex
                CustomChip(
                    chipTitle = item,
                    onClickChip = { onChipSelected(index) },
                    modifier = Modifier.padding(end = 8.dp),
                    isSelected = isSelected,
                    icon = {
//                        Icon(
//                            painter = painterResource(R.drawable.ic_onboarding_vomiting),
//                            modifier = Modifier.size(20.dp),
//                            contentDescription = ""
//                        )
                    }
                )
            }
        }
    }
}

@Composable
fun DatePregnantBar(
    date: String,
    onClickCalendar: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(119.dp)
            .padding(horizontal = 18.dp)
    ) {
        HorizontalDivider(thickness = 2.dp, color = NeodinaryColors.Gray.WGray100)
        OnboardingText("임신 날짜")
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .border(BorderStroke(1.dp, NeodinaryColors.Gray.WGray200))
                .fillMaxWidth()
                .height(49.dp)
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                date,
                style = NeodinaryTypography.Body2_Medium,
                color = NeodinaryColors.Gray.WGray600
            )
            IconButton(onClick = { onClickCalendar() }, modifier = Modifier.size(24.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_onboarding_calendar),
                    contentDescription = ""
                )
            }
        }
    }
}