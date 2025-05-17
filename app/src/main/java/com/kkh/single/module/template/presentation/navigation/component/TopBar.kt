package com.kkh.single.module.template.presentation.navigation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kkh.single.module.template.R
import com.kkh.single.module.template.presentation.theme.NeodinaryColors


@Composable
fun NeodinaryTopBar(
    titleImgResource: Int,
    actionsImgResource: Int,
    onClickMainIcon: () -> Unit = {},
    onClickAddIcon: () -> Unit = {}
){
    HomeTopBar(
        onClickAddIcon = onClickAddIcon,
        onClickMainIcon = onClickMainIcon,
        titleImgResource = titleImgResource,
        actionsImgResource = actionsImgResource
    )
}
@Composable
fun HomeTopBar(
    onClickMainIcon: () -> Unit,
    onClickAddIcon: () -> Unit,
    titleImgResource: Int,
    actionsImgResource: Int
) {
    TopAppBar(
        backgroundColor = NeodinaryColors.White.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp),
        title = {
            IconButton(onClick = { onClickMainIcon() }) {
                Icon(
                    painter = painterResource(titleImgResource),
                    contentDescription = "Localized description",
                    tint = Color.Unspecified
                )
            }
        }, actions = {
            IconButton(onClick = { onClickAddIcon() }) {
                Icon(
                    painter = painterResource(actionsImgResource),
                    contentDescription = "Localized description",
                    tint = Color.Unspecified
                )
            }
        })
}