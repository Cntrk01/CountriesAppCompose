package com.example.countriesapp.presentation.region_subregion.subregion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.countriesapp.R
import com.example.countriesapp.common.Constants.Caribbean
import com.example.countriesapp.common.Constants.Central_Europe
import com.example.countriesapp.common.Constants.Eastern_Africa
import com.example.countriesapp.common.Constants.Eastern_Asia
import com.example.countriesapp.common.Constants.Melanesia
import com.example.countriesapp.common.Constants.Middle_Africa
import com.example.countriesapp.common.Constants.North_America
import com.example.countriesapp.common.Constants.Northern_Africa
import com.example.countriesapp.common.Constants.Northern_Europe
import com.example.countriesapp.common.Constants.SOUTHERN_EUROPE
import com.example.countriesapp.common.Constants.SOUTH_EASTERN_ASIA
import com.example.countriesapp.common.Constants.South_America
import com.example.countriesapp.common.Constants.Southeast_Europe
import com.example.countriesapp.common.Constants.Southern_Africa
import com.example.countriesapp.common.Constants.Southern_Asia
import com.example.countriesapp.common.Constants.Western_Africa
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard
import com.example.countriesapp.presentation.ui_model.ScreenItem

@Composable
fun SubRegionScreen(
    backClick: (() -> Unit)? = null,
    clickSubRegionItem: ((String) -> Unit)? = null
) {
    val subRegionItem = listOf(
        ScreenItem(cardText = "Southern Europe", clickHomeItem = { clickSubRegionItem?.invoke(SOUTHERN_EUROPE) }, backgroundColor = Color.LightGray),
        ScreenItem(cardText = "South-Eastern Asia", clickHomeItem = { clickSubRegionItem?.invoke(SOUTH_EASTERN_ASIA) }, backgroundColor = Color.Blue),
        ScreenItem(cardText = "North America", clickHomeItem = { clickSubRegionItem?.invoke(North_America) }, backgroundColor = Color.Cyan),
        ScreenItem(cardText = "Melanesia", clickHomeItem = { clickSubRegionItem?.invoke(Melanesia) }, backgroundColor = Color.Yellow),
        ScreenItem(cardText = "Central Europe", clickHomeItem = { clickSubRegionItem?.invoke(Central_Europe) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Eastern Africa", clickHomeItem = { clickSubRegionItem?.invoke(Eastern_Africa) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Western Africa", clickHomeItem = { clickSubRegionItem?.invoke(Western_Africa) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Northern Africa", clickHomeItem = { clickSubRegionItem?.invoke(Northern_Africa) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Southern Africa", clickHomeItem = { clickSubRegionItem?.invoke(Southern_Africa) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Northern Europe", clickHomeItem = { clickSubRegionItem?.invoke(Northern_Europe) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Caribbean", clickHomeItem = { clickSubRegionItem?.invoke(Caribbean) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "South America", clickHomeItem = { clickSubRegionItem?.invoke(South_America) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Southeast Europe", clickHomeItem = { clickSubRegionItem?.invoke(Southeast_Europe) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Middle Africa", clickHomeItem = { clickSubRegionItem?.invoke(Middle_Africa) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Southern Asia", clickHomeItem = { clickSubRegionItem?.invoke(Southern_Asia) }, backgroundColor = Color.DarkGray),
        ScreenItem(cardText = "Eastern Asia", clickHomeItem = { clickSubRegionItem?.invoke(Eastern_Asia) }, backgroundColor = Color.DarkGray)
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
        ){
            items(subRegionItem) { subRegionItem ->
                HomeCard(
                    cardText = subRegionItem.cardText,
                    clickHomeCardItem = { subRegionItem.clickHomeItem.invoke(it) },
                    backgroundColor = subRegionItem.backgroundColor
                )
            }
        }
    }
}