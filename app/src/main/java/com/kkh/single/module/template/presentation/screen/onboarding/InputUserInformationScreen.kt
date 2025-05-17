package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kkh.single.module.template.presentation.component.ChipTitle
import com.kkh.single.module.template.presentation.component.CustomBottomButton
import com.kkh.single.module.template.presentation.component.CustomChip
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTheme
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography


/**
 * 임신 날짜, 키, 몸무게, 나이 선택 화면
 */
@Composable
fun InputUserInformationScreen(navController: NavHostController, onNavigateToCalendar: () -> Unit) {

    var selectedIndex by remember { mutableIntStateOf(-1) }
    var selectedIndex2 by remember { mutableIntStateOf(-1) }
    var selectedIndex3 by remember { mutableIntStateOf(-1) }

    val list = listOf("20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70")
    val list2 = listOf("20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70")
    val list3 = listOf("20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70")

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        top = 21.dp,
                        start = 16.dp
                    ),
                text = "간단한 정보를 입력해 주시면\n맞춤형 식단을 추천해 드릴게요.",
                style = NeodinaryTypography.HeadLine2_SemiBold,
                color = NeodinaryColors.Black.Black,
            )

            Spacer(Modifier.height(26.dp))



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

            Spacer(modifier = Modifier.weight(1f))

            CustomBottomButton(onClickButton = { moveToBegunInformationScreen(navController) })

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
                    isSelected = isSelected
                )
            }
        }
    }
}


private fun moveToBegunInformationScreen(navController: NavHostController) =
    navController.navigate(Routes.RECOMMEND_MENU) {
        popUpTo(Routes.INPUT_USER_INFORMATION) {
            inclusive = true
        }
    }

@Preview(showBackground = true)
@Composable
fun InputUserInformationScreenPreview() {
    NeodinaryTheme {
        InputUserInformationScreen(rememberNavController(), onNavigateToCalendar = {})
    }
}