package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.component.ChipTitle
import com.kkh.single.module.template.presentation.component.CustomBottomButton
import com.kkh.single.module.template.presentation.component.CustomChip
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTheme
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography

data class FoodItem(
    val name: String,
    val imageUrl: String
)

data class SelectInfoItem(
    val text: String,
    val imageRes: Int,
)

/**
 * 이전 임신 여부, 입덧 증세, 비건 단계, 기저 질환 선택 / 입력 화면
 *
 */
@Composable
fun RecommendMenuScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
    onNaviGateToCanNotEatFood: () -> Unit) {

    val list = listOf("없음", "2회 이상", "1회")

    val list2 = listOf(
        SelectInfoItem("메스꺼움", R.drawable.ic_onboarding_vomiting),
        SelectInfoItem("식욕저하", R.drawable.ic_onboarding_cancle),
        SelectInfoItem("구토", R.drawable.ic_onboarding_dizziness)
    )

    val list3 = listOf(
        FoodItem(name = "채소", imageUrl = "https://picsum.photos/200"),
        FoodItem(name = "야채", imageUrl = "https://picsum.photos/200"),
        FoodItem(name = "1", imageUrl = "https://picsum.photos/200"),
        FoodItem(name = "2", imageUrl = "https://picsum.photos/200"),
        FoodItem(name = "3", imageUrl = "https://picsum.photos/200"),
        FoodItem(name = "4", imageUrl = "https://picsum.photos/200")
    )

    var selectedIndex by remember { mutableIntStateOf(-1) }
    var selectedIndex2 by remember { mutableIntStateOf(-1) }
    var selectedNames by remember { mutableStateOf<List<String>>(emptyList()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Spacer(Modifier.height(24.dp))
        }, bottomBar = {
            CustomBottomButton(
                enable = selectedIndex != -1 && selectedIndex2 != -1 && selectedNames.isNotEmpty(),
                text = "다음",
                onClickButton = { onNaviGateToCanNotEatFood() })
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "2/3 단계",
                style = NeodinaryTypography.Caption_Medium,
                color = NeodinaryColors.Green.Green400
            )

            SelectCanEatFood(
                list = list3,
                selectedNames = selectedNames,
                onToggleFoodItem = { name ->
                    selectedNames = if (selectedNames.contains(name)) {
                        selectedNames - name
                    } else {
                        selectedNames + name
                    }
                }
            )

            Text(
                modifier = Modifier
                    .padding(
                        top = 21.dp,
                        start = 16.dp
                    ),
                text = "더 정확한 식단을 위해\n" +
                        "00님의 상황을 적어주세요!",
                style = NeodinaryTypography.HeadLine2_SemiBold,
                color = NeodinaryColors.Black.Black,
            )

            Spacer(Modifier.height(26.dp))

            SelectInfoItem(
                text = "임신 여부",
                selectedIndex = selectedIndex,
                onChipSelected = { selectedIndex = it },
                list = list
            )
            SelectInfoItemWithIcon(
                text = "입덧 증세",
                selectedIndex = selectedIndex2,
                onChipSelected = { selectedIndex2 = it },
                list = list2
            )

            var isSpecialChecked by remember { mutableStateOf(false) }
            var textFieldValue by remember { mutableStateOf("") }

            SpecialInfoItem(
                "특이사항",
                isChecked = isSpecialChecked,
                onCheckedChange = { isSpecialChecked = !isSpecialChecked },
                textFieldValue = textFieldValue,
                onTextChanged = { textFieldValue = it }
            )

        }
    }
}


@Composable
fun SelectFoodItem(
    name: String,
    imageUrl: String,
    isSelected: Boolean,
    onClickFoodItem: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClickFoodItem() }
            .size(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(
                BorderStroke(
                    3.dp,
                    if (isSelected) NeodinaryColors.Green.Green400 else NeodinaryColors.Gray.WGray200
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        )
        Box(
            Modifier
                .fillMaxSize()
                .background(NeodinaryColors.Black.Black.copy(alpha = 0.5f))
        )
        Text(
            name,
            style = NeodinaryTypography.Body1_Medium,
            color = NeodinaryColors.White.White
        )
    }
}

@Composable
fun SelectCanEatFood(
    text: String = "먹을 수 있는 것을 모두 선택 해주세요!",
    list: List<FoodItem>,
    selectedNames: List<String>,
    onToggleFoodItem: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        HorizontalDivider(thickness = 2.dp, color = NeodinaryColors.Gray.WGray100)
        OnboardingText(text)
        Spacer(Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(list) { item ->
                val isSelected = selectedNames.contains(item.name)
                SelectFoodItem(
                    name = item.name,
                    imageUrl = item.imageUrl,
                    isSelected = isSelected,
                    onClickFoodItem = { onToggleFoodItem(item.name) }
                )
            }
        }
    }
}

@Composable
fun SpecialInfoItem(
    text: String = "key",
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    textFieldValue: String,
    onTextChanged: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        HorizontalDivider(thickness = 2.dp, color = NeodinaryColors.Gray.WGray100)
        OnboardingText(text)
        Spacer(Modifier.height(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = NeodinaryColors.Green.Green300,
                    uncheckedColor = NeodinaryColors.Gray.WGray400,
                    checkmarkColor = NeodinaryColors.Black.Black
                ),
                checked = isChecked,
                onCheckedChange = { onCheckedChange(it) }
            )
            Text(text = "기저 질환이 있어요")
        }

        if (isChecked) {
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = textFieldValue,
                onValueChange = onTextChanged,
                label = {
                    Text(
                        "질환명을 모두 입력해주세요!",
                        style = NeodinaryTypography.Body2_Medium,
                        color = NeodinaryColors.Gray.WGray600
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Composable
fun SelectInfoItemWithIcon(
    text: String = "key",
    selectedIndex: Int,
    onChipSelected: (Int) -> Unit,
    list: List<SelectInfoItem>
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
            item{
                CustomChip(
                    chipTitle = "없음",
                    onClickChip = { onChipSelected(0) },
                    modifier = Modifier.padding(end = 8.dp),
                    isSelected = selectedIndex == 0,
                )
            }
            itemsIndexed(list) { index, item ->
                val isSelected = index+1 == selectedIndex
                CustomChip(
                    chipTitle = item.text,
                    onClickChip = { onChipSelected(index+1) },
                    modifier = Modifier.padding(end = 8.dp),
                    isSelected = isSelected,
                    icon = {
                        Icon(
                            painter = painterResource(item.imageRes),
                            modifier = Modifier.size(20.dp),
                            tint = Color.Unspecified,
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(2.dp))
                    }
                )
            }
        }
    }
}

private fun moveToCanNotEatFood(navController: NavHostController) =
    navController.navigate(Routes.CAN_NOT_EAT) {
        popUpTo(Routes.RECOMMEND_MENU) {
            inclusive = true
        }
    }

