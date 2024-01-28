package com.example.countriesapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.countriesapp.R
import com.example.countriesapp.common.Constants.ALL_COUNTRY
import com.example.countriesapp.common.Constants.CURRENCY
import com.example.countriesapp.common.Constants.FAVORITE
import com.example.countriesapp.common.Constants.PLAY_QUIZ
import com.example.countriesapp.common.Constants.REGION
import com.example.countriesapp.common.Constants.SUB_REGION
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard

@Composable
fun HomeScreen(
    clickHomeItem : (String)->Unit
){
    Column {
        AppBar(imageId = R.drawable.icons_turkey)

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                HomeCard(imageId = R.drawable.icons_planet_earth,
                    cardText = "All Country", clickHomeCardItem = {
                   clickHomeItem.invoke(ALL_COUNTRY)
                }, backgroundColor = Color.LightGray)
            }
            item {
                HomeCard(imageId = R.drawable.icon_region,
                    cardText = "Region", clickHomeCardItem = {
                   clickHomeItem.invoke(REGION)
                }, backgroundColor = Color.DarkGray)
            }
            item {
                HomeCard(imageId = R.drawable.icon_country_region,
                    cardText = "Sub Region", clickHomeCardItem = {
                    clickHomeItem.invoke(SUB_REGION)
                }, backgroundColor = Color.Cyan)
            }
            item {
                HomeCard(R.drawable.icon_currency_exchange,
                    cardText = "Currency", clickHomeCardItem = {
                    clickHomeItem.invoke(CURRENCY)
                }, backgroundColor = MaterialTheme.colorScheme.tertiary)
            }
            item {
                HomeCard(R.drawable.icon_quiz,
                    cardText = "Play Quiz", clickHomeCardItem = {
                    clickHomeItem.invoke(PLAY_QUIZ)
                }, backgroundColor = Color.Magenta)
            }
            item {
                HomeCard(R.drawable.icon_favorite_home,
                    cardText = "Favorite", clickHomeCardItem = {
                    clickHomeItem.invoke(FAVORITE)
                }, backgroundColor = Color.LightGray)
            }
        }
    }
}