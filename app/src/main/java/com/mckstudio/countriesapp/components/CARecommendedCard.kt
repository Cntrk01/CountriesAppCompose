package com.mckstudio.countriesapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme

data class RecommendedCardModel(
    val countryName: String,
    val countryCapital: String,
    val countryRegion: String,
    val countryImageUrl: String,
)

@Composable
fun CARecommendedCard(
    modifier: Modifier = Modifier,
    data: RecommendedCardModel,
    onClick: (String) -> Unit = {},
) {
    // 1. Card kullanmak gölge ve şekil yönetimi için Box + Shadow'dan daha stabildir.
    Card(
        modifier = modifier
            .padding(Dimens.dp4) // Diğer itemlarla çakışmaması için küçük bir pay
            .fillMaxWidth()
            .clickable { onClick(data.countryName) },
        shape = RoundedCornerShape(Dimens.dp12), // Köşeleri burada belirle
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // 2. Resim Alanı
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.countryImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = data.countryName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    // Alt köşeleri yuvarlama, sadece üstler yuvarlak kalsın
                    .clip(RoundedCornerShape(topStart = Dimens.dp12, topEnd = Dimens.dp12)),
                contentScale = ContentScale.Crop,
                // Resim yüklenene kadar veya hata verirse gri arka plan
                placeholder = null,
            )

            // 3. İçerik Alanı
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.dp12)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f), // Uzun isimlerin taşmasını engeller
                        text = data.countryName,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(Dimens.dp12)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = Dimens.dp8, vertical = Dimens.dp2),
                            text = data.countryRegion,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.dp4))

                Text(
                    text = "Capital: ${data.countryCapital}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CARecommendedCardPreview() {
    CountriesAppTheme(dynamicColor = false) {
        Box(modifier = Modifier.padding(16.dp)) {
            CARecommendedCard(
                data = RecommendedCardModel(
                    countryName = "Switzerland",
                    countryCapital = "Bern",
                    countryRegion = "Europe",
                    countryImageUrl = "https://example.com/swiss.jpg",
                )
            )
        }
    }
}