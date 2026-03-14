package com.mckstudio.countriesapp.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.ui.theme.CABlue
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme
import com.mckstudio.countriesapp.ui.theme.SoftRed
import com.mckstuido.countriesapp.R

@Composable
fun CAError(
    modifier: Modifier = Modifier,
    title: String = "Connection Error",
    description: String = "The server is currently unavailable. Please check your internet connection and try again.",
    retryButtonText: String = "Try Again",
    onRetryClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "scale"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.dp24),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(240.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp * scale)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.1f))
            )

            Card(
                modifier = Modifier.size(160.dp),
                shape = RoundedCornerShape(Dimens.dp24),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = Dimens.dp8)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_wifi_off),
                        contentDescription = null,
                        modifier = Modifier.size(Dimens.dp64),
                        tint = CABlue
                    )
                    Spacer(modifier = Modifier.height(Dimens.dp8))
                    Row(horizontalArrangement = Arrangement.spacedBy(Dimens.dp4)) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(Dimens.dp6)
                                    .clip(CircleShape)
                                    .background(Color.LightGray)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = (-Dimens.dp10), y = (-Dimens.dp10))
                    .size(Dimens.dp48)
                    .clip(CircleShape)
                    .background(SoftRed)
                    .padding(Dimens.dp8),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_wifi_off),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(Dimens.dp24)
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.dp32))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(Dimens.dp12))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = Dimens.dp16)
        )

        Spacer(modifier = Modifier.height(Dimens.dp32))

        Button(
            onClick = onRetryClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.dp56),
            shape = RoundedCornerShape(Dimens.dp16),
            colors = ButtonDefaults.buttonColors(containerColor = CABlue)
        ) {
            Text(
                text = retryButtonText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CAErrorScreenPreview() {
    CountriesAppTheme {
        CAError(onRetryClick = {})
    }
}