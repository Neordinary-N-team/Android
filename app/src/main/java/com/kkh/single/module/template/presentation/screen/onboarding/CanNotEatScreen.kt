package com.kkh.single.module.template.presentation.screen.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kkh.single.module.template.presentation.component.CustomBottomButton
import com.kkh.single.module.template.presentation.navigation.Routes
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTheme
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography

@Composable
fun CanNotEatScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel(),
    navigateToHome: () -> Unit){

    var canNotEatFood by remember { mutableStateOf("") }

    var isSpecialChecked by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Spacer(Modifier.height(24.dp)) },
        bottomBar = {CustomBottomButton(onClickButton = {
            navigateToHome()
        })},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "3/3 단계",
                style = NeodinaryTypography.Caption_Medium,
                color = NeodinaryColors.Green.Green400
            )
            Text(
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        start = 16.dp
                    ),
                text = "기피하거나 못 먹는 식품을 \n" +
                        "자유롭게 적어주세요",
                style = NeodinaryTypography.Splash_SemiBold,
                color = NeodinaryColors.Black.Black,
            )
            Spacer(modifier = Modifier.height(50.dp))
            val text = remember { mutableStateOf("") }

            CustomUnderLinedTextField(text.value, onValueChange = { text.value = it })

        }
    }
}

@Composable
fun CustomUnderLinedTextField(text: String, onValueChange: (String) -> Unit = {}) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent, // 밑줄 색상 (포커스 상태)
                unfocusedIndicatorColor = Color.Transparent, // 밑줄 색상 (비포커스 상태)
            ),
            textStyle = NeodinaryTypography.Body1_Medium,
            placeholder = { Text("입력하세요", style = NeodinaryTypography.Body1_Medium) },
        )
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            thickness = 1.dp,
            color = NeodinaryColors.Green.Green400
        )
    }
}
