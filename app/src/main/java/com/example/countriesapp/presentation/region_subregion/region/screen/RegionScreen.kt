package com.example.countriesapp.presentation.region_subregion.region.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.countriesapp.R
import com.example.countriesapp.common.Constants.AFRICA
import com.example.countriesapp.common.Constants.AMERICAS
import com.example.countriesapp.common.Constants.ANTARCTIC
import com.example.countriesapp.common.Constants.ASIA
import com.example.countriesapp.common.Constants.EUROPE
import com.example.countriesapp.common.Constants.OCEANIA
import com.example.countriesapp.domain.model.ScreenItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard

@Composable
fun RegionScreen(
    backClick: (() -> Unit)? = null,
    clickRegionItem: ((String) -> Unit)? = null
) {
    val regionItems = listOf(
        ScreenItem(imageId = R.drawable.icons_antarctic, cardText = "Antarctic", clickHomeItem = { clickRegionItem?.invoke(ANTARCTIC) }, backgroundColor = MaterialTheme.colorScheme.tertiary),
        ScreenItem(imageId = R.drawable.icons_europe, cardText = "Europe", clickHomeItem = { clickRegionItem?.invoke(EUROPE) }, backgroundColor = MaterialTheme.colorScheme.surfaceTint),
        ScreenItem(imageId = R.drawable.icon_america, cardText = "Americas", clickHomeItem = { clickRegionItem?.invoke(AMERICAS) }, backgroundColor = MaterialTheme.colorScheme.outlineVariant),
        ScreenItem(imageId = R.drawable.icon_africa, cardText = "Africa", clickHomeItem = { clickRegionItem?.invoke(AFRICA) }, backgroundColor = MaterialTheme.colorScheme.scrim),
        ScreenItem(imageId = R.drawable.icon_oceania, cardText = "Oceania", clickHomeItem = { clickRegionItem?.invoke(OCEANIA) }, backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer),
        ScreenItem(imageId = R.drawable.icon_asia, cardText = "Asia", clickHomeItem = { clickRegionItem?.invoke(ASIA) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary),
    )

    Column {
        AppBar(backButtonCheck = true,
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick?.invoke()
            })

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(regionItems){regionItem->
                HomeCard(
                    imageId = regionItem.imageId,
                    cardText = regionItem.cardText,
                    clickHomeCardItem = regionItem.clickHomeItem,
                    backgroundColor = regionItem.backgroundColor
                )
            }
        }
    }
}