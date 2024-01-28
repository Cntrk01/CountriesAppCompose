package com.example.countriesapp.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.countriesapp.R

@Composable
fun LoadingCardView(
    modifier: Modifier = Modifier
) {
    val rememberLottie =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_animation))

    val progressAnimation by animateLottieCompositionAsState(
        composition = rememberLottie.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 1f
    )

    Box(
        modifier = modifier
            .height(150.dp)
            .width(130.dp)
            .zIndex(1f)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .zIndex(1f),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        )
        {
            LottieAnimation(
                composition = rememberLottie.value,
                progress = progressAnimation
            )
            //Text(
            //                modifier = Modifier
            //                    .fillMaxWidth()
            //                    .padding(top = 30.dp), text = "Loading...",
            //                fontSize = 18.sp,
            //                fontFamily = FontFamily.SansSerif,
            //                fontWeight = FontWeight.SemiBold,
            //                textAlign = TextAlign.Center
            //            )
        }
    }


}