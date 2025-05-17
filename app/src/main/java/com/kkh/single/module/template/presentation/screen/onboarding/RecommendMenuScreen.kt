package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kkh.single.module.template.presentation.component.ChipTitle
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTheme
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography

/**
 * 이전 임신 여부, 입덧 증세, 비건 단계, 기저 질환 선택 / 입력 화면
 *
 * @param navController
 */
@Composable
fun RecommendMenuScreen(navController: NavHostController) {
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
                style = NeodinaryTypography.Splash_SemiBold,
                color = NeodinaryColors.Black.Black,
            )

            ChipTitle(
                modifier = Modifier
                    .padding(
                        top = 55.dp,
                        start = 18.dp
                    ),
                titleText = "이전 임신 여부"
            )

            ChipTitle(
                modifier = Modifier
                    .padding(
                        top = 55.dp,
                        start = 18.dp
                    ),
                titleText = "입덧 증세"
            )

            ChipTitle(
                modifier = Modifier
                    .padding(
                        top = 55.dp,
                        start = 18.dp
                    ),
                titleText = "비건 단계"
            )

            ChipTitle(
                modifier = Modifier
                    .padding(
                        top = 55.dp,
                        start = 18.dp
                    ),
                titleText = "기저 질환"
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 26.dp,
                        end = 26.dp,
                        bottom = 48.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeodinaryColors.Green.Green200,
                    contentColor = NeodinaryColors.White.White,
                ),
                shape = RoundedCornerShape(6.dp),
                onClick = {
                    moveToCanNotEatFood(navController)
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 9.dp),
                    text = "다음 (2/3)",
                    style = NeodinaryTypography.OnBoarding_Normal
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

@Preview(showBackground = true)
@Composable
private fun RecommendMenuScreenPreview() {
    NeodinaryTheme {
        RecommendMenuScreen(rememberNavController())
    }
}