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

data class  HomeScreenItem(
    val imageId: Int,
    val cardText: String,
    val clickHomeItem: (String) -> Unit,
    val backgroundColor : Color
)

@Composable
fun HomeScreen(
    clickHomeItem: (String) -> Unit
) {
    val homeItems = listOf(
        HomeScreenItem(imageId = R.drawable.icons_planet_earth, cardText = All_Country,
            clickHomeItem = { clickHomeItem.invoke(All_Country) }, backgroundColor = Color.LightGray),

        HomeScreenItem(imageId = R.drawable.icon_region, cardText = Region,
            clickHomeItem = { clickHomeItem.invoke(Region) }, backgroundColor = Color.DarkGray
        ),

        HomeScreenItem(imageId = R.drawable.icon_country_region, cardText = Sub_Region,
            clickHomeItem = { clickHomeItem.invoke(Sub_Region) }, backgroundColor = Color.Cyan
        ),

        HomeScreenItem(imageId = R.drawable.icon_currency_exchange, cardText = Currency,
            clickHomeItem = { clickHomeItem.invoke(Currency) }, backgroundColor = MaterialTheme.colorScheme.tertiary
        ),

        HomeScreenItem(imageId = R.drawable.icon_quiz, cardText = Play_Quiz,
            clickHomeItem = { clickHomeItem.invoke(Play_Quiz) }, backgroundColor = Color.Magenta
        ),

        HomeScreenItem(imageId = R.drawable.icon_favorite_home, cardText = Favorite,
            clickHomeItem = { clickHomeItem.invoke(Favorite) }, backgroundColor = Color.LightGray
        )
    )

    Column {
        AppBar(imageId = R.drawable.icons_turkey)

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