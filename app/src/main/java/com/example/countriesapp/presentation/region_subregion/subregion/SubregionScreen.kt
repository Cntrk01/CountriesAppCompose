package com.example.countriesapp.presentation.region_subregion.subregion

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
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

@Composable
fun SubRegionScreen(
    backClick: (() -> Unit)? = null,
    clickSubRegionItem: ((String) -> Unit)? = null
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
                HomeCard(cardText = "Southern Europe",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(SOUTHERN_EUROPE)
                    }, backgroundColor = Color.LightGray
                )
            }
            item {
                HomeCard(cardText = "South-Eastern Asia",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(SOUTH_EASTERN_ASIA)
                    }, backgroundColor = Color.Blue
                )
            }
            item {
                HomeCard(
                    cardText = "North America",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(North_America)
                    },
                    backgroundColor = Color.Cyan
                )
            }
            item {
                HomeCard(
                    cardText = "Melanesia",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Melanesia)
                    },
                    backgroundColor = Color.Yellow
                )
            }
            item {
                HomeCard(
                    cardText = "Central Europe",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Central_Europe)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Eastern Africa",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Eastern_Africa)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Western Africa",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Western_Africa)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Northern Africa",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Northern_Africa)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Southern Africa",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Southern_Africa)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Northern Europe",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Northern_Europe)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Caribbean",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Caribbean)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "South America",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(South_America)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Southeast Europe",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Southeast_Europe)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Middle Africa",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Middle_Africa)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Southern Asia",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Southern_Asia)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
            item {
                HomeCard(
                    cardText = "Eastern Asia",
                    clickHomeCardItem = {
                        clickSubRegionItem?.invoke(Eastern_Asia)
                    },
                    backgroundColor = Color.DarkGray
                )
            }
        }
    }
}