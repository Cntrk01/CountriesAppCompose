package com.example.countriesapp.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.countriesapp.R

@Composable
fun AppBar(
    countryName: String,
    backClick: (() -> Unit) ?=null,
    backButtonCheck : Boolean = false
) {
    val checkBackButton = remember {
        mutableStateOf(backButtonCheck)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(70.dp)
            .padding(start = 5.dp)
            .zIndex(1f)
    ) {

        if (checkBackButton.value){
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        backClick?.invoke()
                    }
            )
        }
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = countryName,
            fontSize = 24.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            maxLines = 1
        )
    }
}
