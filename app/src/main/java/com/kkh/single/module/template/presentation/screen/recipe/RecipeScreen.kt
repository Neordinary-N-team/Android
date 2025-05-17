package com.kkh.single.module.template.presentation.screen.recipe

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kkh.single.module.template.R

@Preview
@Composable
fun RecipeScreen() {
    Column(Modifier.fillMaxSize()) {
        RecipeTopImage()
    }
}

@Preview
@Composable
fun RecipeTopImage() {
    Column(
        Modifier
            .fillMaxHeight(0.35f)
            .fillMaxWidth()
    ) {
        AsyncImage(model = "", contentDescription = "", modifier = Modifier.fillMaxSize())
    }
    Box(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        IconButton(onClick = { /* Handle click */ }) {
            androidx.compose.material3.Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "",
                tint = Color.White
            )
        }
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
    }
}