package com.example.countriesapp.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(
    errorMessage: String,
    showButton: Boolean? = true,
    clickRetryButton: (() -> Unit)? = null,
    buttonText : String = "Try Again"
) {
    Column(
        modifier=Modifier.padding(10.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            text = errorMessage,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )

        if (showButton == true) {
            Button(onClick = {
                clickRetryButton?.invoke()
            }) {
                Text(text = buttonText, fontSize = 18.sp)
            }
        }
    }
}