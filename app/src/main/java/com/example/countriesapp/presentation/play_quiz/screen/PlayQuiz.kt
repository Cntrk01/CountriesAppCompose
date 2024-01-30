package com.example.countriesapp.presentation.play_quiz.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.countriesapp.R
import com.example.countriesapp.common.Constants
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard

@Composable
fun PlayQuiz(
    backClick: () -> Unit,
    clickHomeItem: ((String) -> Unit)
) {
    Column {
        AppBar(
            backButtonCheck = true,
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick.invoke()
            })

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(10.dp)
        ) {
            item {
                HomeCard(
                    imageId = R.drawable.icons_planet_earth,
                    cardText = "Easy",
                    clickHomeCardItem = {
                        clickHomeItem.invoke(Constants.EASY)
                    },backgroundColor = Color.LightGray
                )
            }
            item {
                HomeCard(
                    imageId = R.drawable.icon_america,
                    cardText = "Medium",
                    clickHomeCardItem = {
                        clickHomeItem.invoke(Constants.MEDIUM)
                    },backgroundColor = Color.LightGray
                )
            }
            item {
                HomeCard(
                    imageId = R.drawable.icon_favorite_home,
                    cardText = "Hard",
                    clickHomeCardItem = {
                        clickHomeItem.invoke(Constants.HARD)
                    },backgroundColor = Color.LightGray
                )
            }
            item {
                HomeCard(
                    imageId = R.drawable.icons_hungary,
                    cardText = "Expert",
                    clickHomeCardItem = {
                        clickHomeItem.invoke(Constants.EXPERT)
                    },backgroundColor = Color.LightGray
                )
            }
        }
    }
}