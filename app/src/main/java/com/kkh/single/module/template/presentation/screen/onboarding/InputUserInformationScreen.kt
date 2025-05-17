package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
 * 임신 날짜, 키, 몸무게, 나이 선택 화면
 */
@Composable
fun InputUserInformationScreen(navController: NavHostController) {
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
                text = "임신 날짜를 알려주세요!",
                style = NeodinaryTypography.Splash_SemiBold,
                color = NeodinaryColors.Black.Black,
            )

            Spacer(modifier = Modifier.height(38.dp))
            Text(
                modifier = Modifier
                    .size(
                        width = 355.dp,
                        height = 349.dp
                    )
                    .padding(start = 20.dp)
                    .background(NeodinaryColors.Black.Black),
                text = "달력 공간",
                color = NeodinaryColors.White.White
            )
            Spacer(modifier = Modifier.height(38.dp))

            // 키
            ChipTitle(
                modifier = Modifier
                    .padding(
                        start = 18.dp
                    ),
                titleText = "키"
            )

            // 몸무게
            ChipTitle(
                modifier = Modifier
                    .padding(
                        top = 55.dp,
                        start = 18.dp
                    ),
                titleText = "몸무게"
            )

            // 나이
            ChipTitle(
                modifier = Modifier
                    .padding(
                        top = 55.dp,
                        start = 18.dp
                    ),
                titleText = "나이"
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
                    moveToBegunInformationScreen(navController)
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 9.dp),
                    text = "다음 (1/3)",
                    style = NeodinaryTypography.OnBoarding_Normal
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
        InputUserInformationScreen(rememberNavController())
    }
}