package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun CanNotEatScreen(navController: NavHostController) {

    var canNotEatFood by remember { mutableStateOf("") }

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
                text = "기피하거나 못 먹는 식품이 있나요?",
                style = NeodinaryTypography.Splash_SemiBold,
                color = NeodinaryColors.Black.Black,
            )
            Spacer(modifier = Modifier.height(50.dp))
            TextField(
                modifier = Modifier
                    .padding(
                        start = 16.dp
                    ),
                value = canNotEatFood,
                onValueChange = { canNotEatFood = it },
                placeholder = {
                    Text("음식명 입력")
                }
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
                    moveToMain(navController)
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 9.dp),
                    text = "완료",
                    style = NeodinaryTypography.OnBoarding_Normal
                )
            }
        }
    }
}

private fun moveToMain(navController: NavHostController) =
    navController.navigate(Routes.HOME) {
        popUpTo(Routes.CAN_NOT_EAT) {
            inclusive = true
        }
    }

@Preview(showBackground = true)
@Composable
private fun CanNotEatScreenPreview() {
    NeodinaryTheme {
        CanNotEatScreen(rememberNavController())
    }
}