package com.mckstudio.countriesapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.mckstuido.countriesapp.R

@Composable
fun HomeScreen(
    clickHomeItem: (String) -> Unit
) {
    val homeItems = listOf(
        ScreenItem(imageId = R.drawable.icons_planet_earth,
            cardText = All_Country,
            clickHomeItem = { clickHomeItem.invoke(All_Country) },
            backgroundColor = MaterialTheme.colorScheme.primary
        ),

        ScreenItem(
            imageId = R.drawable.icon_region,
            cardText = Region,
            clickHomeItem = { clickHomeItem.invoke(Region) },
            backgroundColor = MaterialTheme.colorScheme.tertiary
        ),

        ScreenItem(
            imageId = R.drawable.icon_country_region,
            cardText = Sub_Region,
            clickHomeItem = { clickHomeItem.invoke(Sub_Region) },
            backgroundColor = MaterialTheme.colorScheme.inversePrimary
        ),

        ScreenItem(
            imageId = R.drawable.icon_currency_exchange,
            cardText = Currency,
            clickHomeItem = { clickHomeItem.invoke(Currency) },
            backgroundColor = MaterialTheme.colorScheme.scrim
        ),

        ScreenItem(
            imageId = R.drawable.icon_quiz,
            cardText = Play_Quiz,
            clickHomeItem = { clickHomeItem.invoke(Play_Quiz) },
            backgroundColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),

        ScreenItem(
            imageId = R.drawable.icon_favorite_home,
            cardText = Favorite,
            clickHomeItem = { clickHomeItem.invoke(Favorite) },
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        ScreenItem(
            imageId = R.drawable.icons_search,
            cardText = SearchCountry,
            clickHomeItem = { clickHomeItem.invoke(SearchCountry) },
            backgroundColor = MaterialTheme.colorScheme.primaryContainer
        )
    )

    Column {
        AppBar(imageId = R.drawable.icon_app_bar, backButtonCheck = false)

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