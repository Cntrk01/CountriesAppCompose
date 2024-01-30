package com.example.countriesapp.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalLine(
    heightDp:Dp
){
    Box(
        modifier = Modifier
            .height(heightDp)
            .fillMaxWidth()
            .background(Color.Black)
    )
}