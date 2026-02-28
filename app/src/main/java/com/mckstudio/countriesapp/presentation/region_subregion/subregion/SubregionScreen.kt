package com.mckstudio.countriesapp.presentation.region_subregion.subregion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mckstudio.countriesapp.common.Constants.Caribbean
import com.mckstudio.countriesapp.common.Constants.Central_Europe
import com.mckstudio.countriesapp.common.Constants.Eastern_Africa
import com.mckstudio.countriesapp.common.Constants.Eastern_Asia
import com.mckstudio.countriesapp.common.Constants.Melanesia
import com.mckstudio.countriesapp.common.Constants.Middle_Africa
import com.mckstudio.countriesapp.common.Constants.North_America
import com.mckstudio.countriesapp.common.Constants.Northern_Africa
import com.mckstudio.countriesapp.common.Constants.Northern_Europe
import com.mckstudio.countriesapp.common.Constants.SOUTHERN_EUROPE
import com.mckstudio.countriesapp.common.Constants.SOUTH_EASTERN_ASIA
import com.mckstudio.countriesapp.common.Constants.South_America
import com.mckstudio.countriesapp.common.Constants.Southeast_Europe
import com.mckstudio.countriesapp.common.Constants.Southern_Africa
import com.mckstudio.countriesapp.common.Constants.Southern_Asia
import com.mckstudio.countriesapp.common.Constants.Western_Africa
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.HomeCard
import com.mckstudio.countriesapp.domain.model.ScreenItem
import com.mckstuido.countriesapp.R

@Composable
fun SubRegionScreen(
    backClick: (() -> Unit)? = null,
    clickSubRegionItem: ((String) -> Unit)? = null
) {
    val subRegionItem = listOf(
        ScreenItem(imageId=  R.drawable.icons_southern_europe,cardText = stringResource(R.string.southern_europe), clickHomeItem = { clickSubRegionItem?.invoke(SOUTHERN_EUROPE) }, backgroundColor = MaterialTheme.colorScheme.outline),
        ScreenItem(imageId = R.drawable.icons_eastern_asia,cardText = stringResource(R.string.south_eastern_asia), clickHomeItem = { clickSubRegionItem?.invoke(SOUTH_EASTERN_ASIA) }, backgroundColor = MaterialTheme.colorScheme.tertiaryContainer),
        ScreenItem(imageId = R.drawable.icons_north_america, cardText = stringResource(R.string.north_america), clickHomeItem = { clickSubRegionItem?.invoke(North_America) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary),
        ScreenItem(imageId = R.drawable.icons_planet_earth, cardText = stringResource(R.string.melanesia), clickHomeItem = { clickSubRegionItem?.invoke(Melanesia) }, backgroundColor = MaterialTheme.colorScheme.secondaryContainer),
        ScreenItem(imageId = R.drawable.icons_central_europe,cardText = stringResource(R.string.central_europe), clickHomeItem = { clickSubRegionItem?.invoke(Central_Europe) }, backgroundColor = MaterialTheme.colorScheme.secondary),
        ScreenItem(imageId = R.drawable.icons_eastern_africa,cardText = stringResource(R.string.eastern_africa), clickHomeItem = { clickSubRegionItem?.invoke(Eastern_Africa) }, backgroundColor = MaterialTheme.colorScheme.tertiaryContainer),
        ScreenItem(imageId = R.drawable.icons_western_africa,cardText = stringResource(R.string.western_africa), clickHomeItem = { clickSubRegionItem?.invoke(Western_Africa) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary),
        ScreenItem(imageId = R.drawable.icon_africa,cardText = stringResource(R.string.northern_africa), clickHomeItem = { clickSubRegionItem?.invoke(Northern_Africa) }, backgroundColor = MaterialTheme.colorScheme.secondary),
        ScreenItem(imageId = R.drawable.icons_southern_africa,cardText = stringResource(R.string.southern_africa), clickHomeItem = { clickSubRegionItem?.invoke(Southern_Africa) }, backgroundColor = MaterialTheme.colorScheme.scrim),
        ScreenItem(imageId = R.drawable.icons_northern_europe,cardText = stringResource(R.string.northern_europe), clickHomeItem = { clickSubRegionItem?.invoke(Northern_Europe) }, backgroundColor = MaterialTheme.colorScheme.outline),
        ScreenItem(imageId = R.drawable.icons_caribbean, cardText = stringResource(R.string.caribbean), clickHomeItem = { clickSubRegionItem?.invoke(Caribbean) }, backgroundColor = MaterialTheme.colorScheme.outlineVariant),
        ScreenItem(imageId = R.drawable.icons_south_america,cardText = stringResource(R.string.south_america), clickHomeItem = { clickSubRegionItem?.invoke(South_America) }, backgroundColor = MaterialTheme.colorScheme.surfaceVariant),
        ScreenItem(imageId = R.drawable.icons_southern_europe,cardText = stringResource(R.string.southeast_europe), clickHomeItem = { clickSubRegionItem?.invoke(Southeast_Europe) }, backgroundColor = MaterialTheme.colorScheme.outline),
        ScreenItem(imageId = R.drawable.icons_southern_africa,cardText = stringResource(R.string.middle_africa), clickHomeItem = { clickSubRegionItem?.invoke(Middle_Africa) }, backgroundColor = MaterialTheme.colorScheme.outlineVariant),
        ScreenItem(imageId = R.drawable.icons_southern_asia,cardText = stringResource(R.string.southern_asia), clickHomeItem = { clickSubRegionItem?.invoke(Southern_Asia) }, backgroundColor = MaterialTheme.colorScheme.errorContainer),
        ScreenItem(imageId = R.drawable.icons_east_asia, cardText = stringResource(R.string.eastern_asia), clickHomeItem = { clickSubRegionItem?.invoke(Eastern_Asia) }, backgroundColor = MaterialTheme.colorScheme.onTertiaryContainer)
    )

    Column {
        AppBar(
            imageId = R.drawable.icon_app_bar,
            backClick = {
                backClick?.invoke()
            })

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ){
            items(subRegionItem) { subRegionItem ->
                HomeCard(
                    imageId=subRegionItem.imageId,
                    cardText = subRegionItem.cardText,
                    clickHomeCardItem = { subRegionItem.clickHomeItem.invoke(it) },
                    backgroundColor = subRegionItem.backgroundColor
                )
            }
        }
    }
}