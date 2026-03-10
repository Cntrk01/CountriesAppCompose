package com.mckstudio.countriesapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.ui.theme.*
import com.mckstuido.countriesapp.R

/**
 * Kartın veri modelini temsil eder.
 * @param title Kartın ana başlığı (Örn: All Countries)
 * @param subtitle Kartın alt başlığı (Örn: 250+ nations)
 * @param iconRes Kullanılacak olan SVG ikon kaynağı
 * @param iconBackgroundColor İkonun arkasındaki soft renk (Color.kt'den gelir)
 */
data class CategoryCardModel(
    val title: String,
    val subtitle: String,
    val iconRes: Int,
    val iconBackgroundColor: Color,
)

@Composable
fun CACategoryCard(
    data: CategoryCardModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(Dimens.dp24))
            .shadow(
                elevation = Dimens.dp1,
                shape = RoundedCornerShape(Dimens.dp24),
                spotColor = MaterialTheme.colorScheme.secondary,
            )
            .clickable { onClick() },
        shape = RoundedCornerShape(Dimens.dp24),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface // Saf Beyaz (CACardWhite)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Icon(
                painter = painterResource(id = data.iconRes),
                contentDescription = null,
                tint = data.iconBackgroundColor.copy(alpha = 0.5f),
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 20.dp, y = 20.dp)
            )

            // İçerik Kolonu
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.dp20),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Box(
                    modifier = Modifier
                        .size(Dimens.dp48)
                        .background(
                            color = data.iconBackgroundColor.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(Dimens.dp16)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = data.iconRes),
                        contentDescription = null,
                        tint = data.iconBackgroundColor,
                        modifier = Modifier.size(Dimens.dp24)
                    )
                }

                Column {
                    Text(
                        text = data.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(Dimens.dp4))
                    Text(
                        text = data.subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CACategoryCardPreview() {
    CountriesAppTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            CACategoryCard(
                data = CategoryCardModel(
                    title = "All Countries",
                    subtitle = "250+ nations",
                    iconRes = R.drawable.icon_search,
                    iconBackgroundColor = CABlue,
                ),
                onClick = {}
            )
        }
    }
}