package com.example.countriesapp.presentation.home

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
import com.example.countriesapp.common.Constants.All_Country
import com.example.countriesapp.common.Constants.Currency
import com.example.countriesapp.common.Constants.Favorite
import com.example.countriesapp.common.Constants.Play_Quiz
import com.example.countriesapp.common.Constants.Region
import com.example.countriesapp.common.Constants.Sub_Region
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard
import com.example.countriesapp.domain.model.ScreenItem

@Composable
fun HomeScreen(
    clickHomeItem: (String) -> Unit
) {
    val homeItems = listOf(
        ScreenItem(imageId = R.drawable.icons_planet_earth, cardText = All_Country,
            clickHomeItem = { clickHomeItem.invoke(All_Country) }, backgroundColor = MaterialTheme.colorScheme.primary),

        ScreenItem(imageId = R.drawable.icon_region, cardText = Region,
            clickHomeItem = { clickHomeItem.invoke(Region) }, backgroundColor = MaterialTheme.colorScheme.tertiary
        ),

        ScreenItem(imageId = R.drawable.icon_country_region, cardText = Sub_Region,
            clickHomeItem = { clickHomeItem.invoke(Sub_Region) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary
        ),

        ScreenItem(imageId = R.drawable.icon_currency_exchange, cardText = Currency,
            clickHomeItem = { clickHomeItem.invoke(Currency) }, backgroundColor =  MaterialTheme.colorScheme.scrim
        ),

        ScreenItem(imageId = R.drawable.icon_quiz, cardText = Play_Quiz,
            clickHomeItem = { clickHomeItem.invoke(Play_Quiz) }, backgroundColor =  MaterialTheme.colorScheme.onPrimaryContainer
        ),

        ScreenItem(imageId = R.drawable.icon_favorite_home, cardText = Favorite,
            clickHomeItem = { clickHomeItem.invoke(Favorite) }, backgroundColor =  MaterialTheme.colorScheme.tertiaryContainer
        )
    )

    Column {
        AppBar(imageId = R.drawable.icons_turkey,backButtonCheck=false)

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(homeItems) { homeItem ->
                HomeCard(
                    imageId = homeItem.imageId,
                    cardText = homeItem.cardText,
                    clickHomeCardItem = homeItem.clickHomeItem,
                    backgroundColor = homeItem.backgroundColor
                )
            }
        }
    }
}