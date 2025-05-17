package com.kkh.single.module.template.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography

@Composable
fun CustomBottomButton(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit,
    enable : Boolean = true
){
    Button(
        enabled = enable,
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                horizontal = 20.dp
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = NeodinaryColors.Green.Green200,
            contentColor = NeodinaryColors.White.White,
        ),
        shape = RoundedCornerShape(6.dp),
        onClick = {
            onClickButton()
        }
    ) {
        Text(
            modifier = Modifier.padding(vertical = 9.dp),
            text = "완료",
            style = NeodinaryTypography.OnBoarding_Normal
        )
    }
}