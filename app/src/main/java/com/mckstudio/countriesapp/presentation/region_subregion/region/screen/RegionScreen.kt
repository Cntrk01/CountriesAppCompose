package com.mckstudio.countriesapp.presentation.region_subregion.region.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mckstudio.countriesapp.common.Constants.AFRICA
import com.mckstudio.countriesapp.common.Constants.AMERICAS
import com.mckstudio.countriesapp.common.Constants.ANTARCTIC
import com.mckstudio.countriesapp.common.Constants.ASIA
import com.mckstudio.countriesapp.common.Constants.EUROPE
import com.mckstudio.countriesapp.common.Constants.OCEANIA
import com.mckstudio.countriesapp.domain.model.ScreenItem
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.HomeCard
import com.mckstuido.countriesapp.R

@Composable
fun RegionScreen(
    backClick: (() -> Unit)? = null,
    clickRegionItem: ((String) -> Unit)? = null
) {
    val regionItems = listOf(
        ScreenItem(imageId = R.drawable.icons_antarctic, cardText = stringResource(R.string.antarctic), clickHomeItem = { clickRegionItem?.invoke(ANTARCTIC) }, backgroundColor = MaterialTheme.colorScheme.tertiary),
        ScreenItem(imageId = R.drawable.icons_europe, cardText = stringResource(R.string.europe), clickHomeItem = { clickRegionItem?.invoke(EUROPE) }, backgroundColor = MaterialTheme.colorScheme.surfaceTint),
        ScreenItem(imageId = R.drawable.icon_america, cardText = stringResource(R.string.americas), clickHomeItem = { clickRegionItem?.invoke(AMERICAS) }, backgroundColor = MaterialTheme.colorScheme.outlineVariant),
        ScreenItem(imageId = R.drawable.icon_africa, cardText = stringResource(R.string.africa), clickHomeItem = { clickRegionItem?.invoke(AFRICA) }, backgroundColor = MaterialTheme.colorScheme.scrim),
        ScreenItem(imageId = R.drawable.icon_oceania, cardText = stringResource(R.string.oceania), clickHomeItem = { clickRegionItem?.invoke(OCEANIA) }, backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer),
        ScreenItem(imageId = R.drawable.icon_asia, cardText = stringResource(R.string.asia), clickHomeItem = { clickRegionItem?.invoke(ASIA) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary),
    )

    Column {
        AppBar(backButtonCheck = true,
            imageId = R.drawable.icon_app_bar,
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