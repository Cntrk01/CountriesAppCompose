package com.example.countriesapp.presentation.region_subregion.region.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard

@Composable
fun RegionScreen(
    backClick: (() -> Unit)? = null,
    clickRegionItem: ((String) -> Unit)? = null
) {
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
            item {
                HomeCard(imageId = R.drawable.icons_antarctic,
                    cardText = "Antarctic", clickHomeCardItem = {
                        clickRegionItem?.invoke(ANTARCTIC)
                    }, backgroundColor = Color.LightGray
                )
            }
            item {
                HomeCard(imageId = R.drawable.icons_europe,
                    cardText = "Europe", clickHomeCardItem = {
                        clickRegionItem?.invoke(EUROPE)
                    }, backgroundColor = Color.Blue
                )
            }
            item {
                HomeCard(imageId = R.drawable.icon_america,
                    cardText = "Americas", clickHomeCardItem = {
                        clickRegionItem?.invoke(AMERICAS)
                    }, backgroundColor = Color.Cyan
                )
            }
            item {
                HomeCard(imageId = R.drawable.icon_africa,
                    cardText = "Africa", clickHomeCardItem = {
                        clickRegionItem?.invoke(AFRICA)
                    }, backgroundColor = MaterialTheme.colorScheme.tertiary
                )
            }
            item {
                HomeCard(imageId = R.drawable.icon_oceania,
                    cardText = "Oceania", clickHomeCardItem = {
                        clickRegionItem?.invoke(OCEANIA)
                    }, backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(imageId = R.drawable.icon_asia,
                    cardText = "Asia", clickHomeCardItem = {
                        clickRegionItem?.invoke(ASIA)
                    }, backgroundColor = Color.DarkGray
                )
            }
        }
    }
}