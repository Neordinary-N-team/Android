package com.kkh.single.module.template.presentation.screen.selectdate

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kkh.single.module.template.R

@Composable
fun SelectDateScreen() {

    Scaffold(topBar = {
        SelectDateTopBar()
    }) { innerPadding ->
        Column(Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            // 캘린더 화면 추가 예정.
        }
    }
}

@Composable
fun SelectDateTopBar() {
    Row(Modifier.fillMaxWidth()) {
        Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = "")
        Spacer(Modifier.width(10.dp))
        Text(text = "날짜 선택하기")
    }
}