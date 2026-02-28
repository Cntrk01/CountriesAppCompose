package com.mckstudio.countriesapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mckstudio.countriesapp.common.Constants.All_Country
import com.mckstudio.countriesapp.common.Constants.Currency
import com.mckstudio.countriesapp.common.Constants.Favorite
import com.mckstudio.countriesapp.common.Constants.Play_Quiz
import com.mckstudio.countriesapp.common.Constants.Region
import com.mckstudio.countriesapp.common.Constants.SearchCountry
import com.mckstudio.countriesapp.common.Constants.Sub_Region
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.HomeCard
import com.mckstudio.countriesapp.domain.model.ScreenItem
import com.mckstudio.countriesapp.layouts.BannerAd
import com.mckstudio.countriesapp.layouts.BaseComposable
import com.mckstuido.countriesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    clickHomeItem: (String) -> Unit
) {
    val homeItems = listOf(
        ScreenItem(imageId = R.drawable.icons_planet_earth,
            cardText = stringResource(id = R.string.all_country),
            clickHomeItem = { clickHomeItem.invoke(All_Country) },
            backgroundColor = MaterialTheme.colorScheme.primary
        ),

        ScreenItem(
            imageId = R.drawable.icon_region,
            cardText = stringResource(id = R.string.region),
            clickHomeItem = { clickHomeItem.invoke(Region) },
            backgroundColor = MaterialTheme.colorScheme.tertiary
        ),

        ScreenItem(
            imageId = R.drawable.icon_country_region,
            cardText = stringResource(id = R.string.sub_region),
            clickHomeItem = { clickHomeItem.invoke(Sub_Region) },
            backgroundColor = MaterialTheme.colorScheme.inversePrimary
        ),

        ScreenItem(
            imageId = R.drawable.icon_currency_exchange,
            cardText = stringResource(id = R.string.currency),
            clickHomeItem = { clickHomeItem.invoke(Currency) },
            backgroundColor = MaterialTheme.colorScheme.scrim
        ),

        ScreenItem(
            imageId = R.drawable.icon_quiz,
            cardText =stringResource(id = R.string.play_quiz),
            clickHomeItem = { clickHomeItem.invoke(Play_Quiz) },
            backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),

        ScreenItem(
            imageId = R.drawable.icon_favorite_home,
            cardText = stringResource(id = R.string.favorite),
            clickHomeItem = { clickHomeItem.invoke(Favorite) },
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        ScreenItem(
            imageId = R.drawable.icons_search,
            cardText = stringResource(id = R.string.search_country),
            clickHomeItem = { clickHomeItem.invoke(SearchCountry) },
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )
    )

    BaseComposable(
        content = { modifier ->
            Column (
                modifier = modifier,
            ){
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(count = homeItems.size,
                        key = {
                            homeItems[it].cardText
                        },
                        itemContent = { homeItemPosition ->
                            HomeCard(
                                imageId = homeItems[homeItemPosition].imageId,
                                cardText = homeItems[homeItemPosition].cardText,
                                clickHomeCardItem = homeItems[homeItemPosition].clickHomeItem,
                                backgroundColor = homeItems[homeItemPosition].backgroundColor
                            )
                        })
                }
            }
        }
    )
}