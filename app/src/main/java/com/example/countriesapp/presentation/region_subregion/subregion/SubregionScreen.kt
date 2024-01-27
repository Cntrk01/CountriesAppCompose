package com.example.countriesapp.presentation.region_subregion.subregion

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.countriesapp.R
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard

@Composable
fun SubRegionScreen(
    backClick: (() -> Unit)? = null,
    clickSubRegionItem: ((String) -> Unit)? = null
) {
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
            HomeCard(cardText = "Southern Europe", clickHomeCardItem = {
                //clickSubRegionItem?.invoke()
            }, backgroundColor = Color.LightGray)
        }
        item {
            HomeCard(cardText = "South-Eastern Asia", clickHomeCardItem = {
                //clickSubRegionItem?.invoke()
            }, backgroundColor = Color.Blue)
        }
        item {
            HomeCard(
                cardText = "North America",
                clickHomeCardItem = {},
                backgroundColor = Color.Cyan
            )
        }
        item {
            HomeCard(cardText = "Melanesia", clickHomeCardItem = {}, backgroundColor = Color.Yellow)
        }
        item {
            HomeCard(
                cardText = "Central Europe",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Eastern Africa",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Western Africa",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Northern Africa",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Southern Africa",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Northern Europe",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Caribbean",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "South America",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Southeast Europe",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Middle Africa",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Southern Asia",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
        item {
            HomeCard(
                cardText = "Eastern Asia",
                clickHomeCardItem = {},
                backgroundColor = Color.DarkGray
            )
        }
    }
}