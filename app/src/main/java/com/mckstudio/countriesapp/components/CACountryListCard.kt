package com.mckstudio.countriesapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.mckstudio.countriesapp.common.Dimens
import com.mckstudio.countriesapp.domain.model.CountryItem
import com.mckstuido.countriesapp.R

@Composable
fun CACountryListCard(
    countries: List<CountryItem>,
    onCountrySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = Dimens.dp8,
            bottom = Dimens.dp16,
        ),
        verticalArrangement = Arrangement.spacedBy(Dimens.dp4)
    ) {
        items(
            items = countries,
            key = { it.name ?: it.hashCode() }
        ) { country ->
            CountryCardItem(
                country = country,
                onCountryClick = onCountrySelected
            )
        }
    }
}


@Composable
fun CountryCardItem(
    country: CountryItem,
    onCountryClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.dp16, vertical = Dimens.dp6)
            .clip(shape = RoundedCornerShape(Dimens.dp12))
            .clickable { onCountryClick(country.name.toString()) },
        shape = RoundedCornerShape(Dimens.dp12),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.dp2),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(Dimens.dp12)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(country.flag),
                contentDescription = "${country.name} Flag",
                modifier = Modifier
                    .size(Dimens.dp56)
                    .clip(RoundedCornerShape(Dimens.dp12)),
                contentScale = ContentScale.Crop,
            )
            // AsyncImage
            //androidx.compose.runtime.ComposePausableCompositionException: Failed to execute op number 24:
            //0: down LayoutNode@0613692 children: 1 measurePolicy: BoxMeasurePolicy(alignment=BiasAlignment(horizontalBias=-1.0, verticalBias=-1.0), propagateMinConstraints=true) deactivated: false
            //1: reuse LayoutNode@0613692 children: 1 measurePolicy: BoxMeasurePolicy(alignment=BiasAlignment(horizontalBias=-1.0, verticalBias=-1.0), propagateMinConstraints=true) deactivated: false
            //2: apply Function2<androidx.compose.ui.node.ComposeUiNode, androidx.compose.ui.layout.MeasurePolicy, kotlin.Unit>
            //3: apply Function2<androidx.compose.ui.node.ComposeUiNode, androidx.compose.runtime.CompositionLocalMap, kotlin.Unit>
            //gibi bir hata alıyordu.Image ile sorunu çözdüm
            //Sorun aslında ImageRequestin bir size(200) olmamasıydı.
            // Ve contentscale eğer none verilirse async image içerisinde
            // updateRequest methodundaki if kontrolinde eğer contentScale None gelirse otomatik olarak  SizeResolver(CoilSize.ORIGINAL) oluşturuyor ve görselin size belirlenmiş oluyor
            //ama değilse mesela Crop verdik diyelim bu durumda else blogu çalışıp remember { ConstraintsSizeResolver() } oluşturuluyor.
            //AsyncImage(
            //    model = ImageRequest.Builder(context)
            //        .data(country.flag?.png)
            //        .crossfade(true) // Pürüzsüz geçiş sağlar
            //        .diskCacheKey(country.name) // Cache için isim kullanır
            //        .memoryCacheKey(country.name)
            //        .build(),
            //    contentDescription = "${country.name} Flag",
            //    modifier = Modifier
            //        .size(Dimens.dp56)
            //        .clip(RoundedCornerShape(Dimens.dp12)),
            //    contentScale = ContentScale.Crop,
            //)

            Spacer(modifier = Modifier.width(Dimens.dp16))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = country.name ?: "Unknown",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
                Text(
                    text = country.capital ?: "No Capital",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = "Go to details",
                tint = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.size(Dimens.dp24)
            )
        }
    }
}
