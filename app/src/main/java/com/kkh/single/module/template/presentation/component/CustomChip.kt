package com.kkh.single.module.template.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kkh.single.module.template.presentation.theme.NeodinaryColors
import com.kkh.single.module.template.presentation.theme.NeodinaryTypography

@Composable
fun CustomChip(
    chipTitle : String,
    onClickChip: () -> Unit = {},
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    icon : @Composable () -> Unit = {}
) {
    val textColor = if (isSelected) NeodinaryColors.White.White else NeodinaryColors.Gray.WGray600
    val chipBackgroundColor = if (isSelected) NeodinaryColors.Black.Black else NeodinaryColors.White.White
    val strokeColor = if (isSelected) NeodinaryColors.Black.Black else NeodinaryColors.Gray.WGray300

    SuggestionChip(
        modifier = modifier.padding(horizontal = 5.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = chipBackgroundColor),
        border = BorderStroke(1.dp, strokeColor),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onClickChip()
        },
        label = {
            Row{
                icon
                Text(
                    text = chipTitle,
                    style = NeodinaryTypography.OnBoarding_Normal,
                    color = textColor
                )
            }
        }
    )
}

@Composable
fun ChipTitle(
    modifier: Modifier = Modifier,
    titleText: String,
) {
    Text(
        modifier = modifier,
        text = titleText,
        style = NeodinaryTypography.OnBoarding_Normal,
        color = NeodinaryColors.Gray.wGray_A9
    )
}