package com.example.countriesapp.presentation.play_quiz.screen

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
import com.example.countriesapp.common.Constants
import com.example.countriesapp.domain.model.ScreenItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.HomeCard

@Composable
fun PlayQuiz(
    backClick: () -> Unit,
    clickHomeItem: ((String) -> Unit)
) {
    val quizItems = listOf(
        ScreenItem(imageId = R.drawable.icons_planet_earth, cardText = Constants.EASY, clickHomeItem = { clickHomeItem.invoke(Constants.EASY) }, backgroundColor = Color.Green),
        ScreenItem(imageId = R.drawable.icon_america, cardText = Constants.MEDIUM, clickHomeItem = { clickHomeItem.invoke(Constants.MEDIUM) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary),
        ScreenItem(imageId = R.drawable.icon_favorite_home, cardText = Constants.HARD, clickHomeItem = { clickHomeItem.invoke(Constants.HARD) }, backgroundColor = Color.Red),
        ScreenItem(imageId = R.drawable.icons_hungary, cardText = Constants.EXPERT, clickHomeItem = { clickHomeItem.invoke(Constants.EXPERT) }, backgroundColor = MaterialTheme.colorScheme.onErrorContainer),
    )

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
            items(quizItems) { quizItems ->
                HomeCard(
                    imageId = quizItems.imageId,
                    cardText = quizItems.cardText,
                    clickHomeCardItem = quizItems.clickHomeItem,
                    backgroundColor = quizItems.backgroundColor
                )
            }
        }
    }
}