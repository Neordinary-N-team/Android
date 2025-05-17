package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTheme
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
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
                text = "나에게 꼭 맞는 식단을 위해\n몇 가지 질문을 드릴게요",
                style = NeodinaryTypography.Splash_SemiBold,
                color = NeodinaryColors.Black.Black,
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 28.dp,
                        end = 28.dp,
                        bottom = 48.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeodinaryColors.Green.Green200,
                    contentColor = NeodinaryColors.White.White,
                ),
                shape = RoundedCornerShape(6.dp),
                onClick = {
                    moveToCalendarScreen(navController)
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 9.dp),
                    text = "시작하기",
                    style = NeodinaryTypography.OnBoarding_Normal
                )
            }
        }
    }
}

private fun moveToCalendarScreen(navController: NavHostController) =
    navController.navigate(Routes.INPUT_USER_INFORMATION) {
        popUpTo(Routes.ONBOARDING) {
            inclusive = true
        }
    }

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    NeodinaryTheme {
        OnBoardingScreen(rememberNavController())
    }
}