package com.example.countriesapp.domain.model

import androidx.compose.ui.graphics.Color

data class ScreenItem(
    val imageId: Int? = null,
    val cardText: String,
    val clickHomeItem: (String) -> Unit,
    val backgroundColor: Color
)
