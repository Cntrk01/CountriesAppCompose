package com.mckstudio.countriesapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.ui.theme.CountriesAppTheme
import com.mckstudio.countriesapp.ui.theme.SoftBlue

data class RecommendedCardModel(
    val title: String,
    val capital: String,
    val region: String,
    val imageUrl: String,
    val onClick: () -> Unit
)

@Composable
fun CARecommendedCard(
    modifier: Modifier = Modifier,
    data: RecommendedCardModel
) {
    Card(
        modifier = modifier
            .width(280.dp) // Görseldeki yatay genişlik
            .clip(RoundedCornerShape(Dimens.dp24))
            .clickable { data.onClick() },
        shape = RoundedCornerShape(Dimens.dp24),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(Dimens.dp20))
            ) {
                AsyncImage(
                    model = data.imageUrl,
                    contentDescription = data.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(Dimens.dp8)
                        .background(
                            color = SoftBlue.copy(alpha = 0.9f), // Temadaki soft mavi
                            shape = RoundedCornerShape(Dimens.dp12)
                        )
                        .padding(horizontal = Dimens.dp12, vertical = Dimens.dp4)
                ) {
                    Text(
                        text = data.region,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary, // CABlue
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Column(modifier = Modifier.padding(Dimens.dp12)) {

                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Text(
                    text = "Capital: ${data.capital}",
                    style = MaterialTheme.typography.bodyMedium,
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
                    title = "Switzerland",
                    capital = "Bern",
                    region = "Europe",
                    imageUrl = "https://example.com/swiss.jpg",
                    onClick = {}
                )
            )
        }
    }
}