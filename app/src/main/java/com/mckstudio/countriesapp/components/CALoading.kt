package com.mckstudio.countriesapp.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
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
import com.mckstuido.countriesapp.R

@Composable
fun CALoading(
    modifier: Modifier = Modifier,
    progress: Int = 65,
    statusText: String,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000,
                easing = LinearEasing,
            ),
            repeatMode = RepeatMode.Restart,
        ), label = "icon_rotate"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFF8FAFF), Color(0xFFE0E9FF))
                )
            )
            .padding(Dimens.dp24),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(280.dp)
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(180.dp + (index * 40).dp)
                        .clip(CircleShape)
                        .background(CABlue.copy(alpha = 0.05f))
                )
            }

            Surface(
                modifier = Modifier.size(100.dp),
                shape = CircleShape,
                color = CABlue,
                shadowElevation = Dimens.dp8
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_compass),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(Dimens.dp20)
                        .rotate(rotation)
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimens.dp32))

        Text(
            text = "Get Ready to \nExplore the World...",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 36.sp,
                textAlign = TextAlign.Center
            ),
            color = Color(0xFF1A1C1E)
        )

        Spacer(modifier = Modifier.height(Dimens.dp16))

        Text(
            text = "We prepare the most up-to-date country data and travel routes for you.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = Dimens.dp16)
        )

        Spacer(modifier = Modifier.height(Dimens.dp48))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.dp8),
            shape = RoundedCornerShape(Dimens.dp24),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(Dimens.dp20)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.icon_sync),
                            contentDescription = null,
                            tint = CABlue,
                            modifier = Modifier.size(Dimens.dp18).rotate(rotation)
                        )
                        Spacer(modifier = Modifier.width(Dimens.dp8))
                        Text(
                            text = statusText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Surface(
                        color = CABlue.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(Dimens.dp8)
                    ) {
                        Text(
                            text = "%$progress",
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = CABlue,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.dp16))

                LinearProgressIndicator(
                    progress = { progress / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimens.dp10)
                        .clip(CircleShape),
                    color = CABlue,
                    trackColor = MaterialTheme.colorScheme.onPrimary,
                )

                Spacer(modifier = Modifier.height(Dimens.dp16))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_security),
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(Dimens.dp24)
                    )
                    Spacer(modifier = Modifier.width(Dimens.dp6))
                    Text(
                        text = "Data is being synchronized with secure servers.",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.LightGray,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "POWERED BY GLOBAL DATA ENGINE",
            style = MaterialTheme.typography.labelMedium,
            color = Color.LightGray.copy(alpha = 0.7f),
            letterSpacing = 1.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CALoadingPreview() {
    CountriesAppTheme {
        CALoading(
            statusText = "Loading Country"
        )
    }
}