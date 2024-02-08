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
        ScreenItem(imageId = R.drawable.icons_medium, cardText = Constants.MEDIUM, clickHomeItem = { clickHomeItem.invoke(Constants.MEDIUM) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary),
        ScreenItem(imageId = R.drawable.icons_hard, cardText = Constants.HARD, clickHomeItem = { clickHomeItem.invoke(Constants.HARD) }, backgroundColor = Color.Red),
        ScreenItem(imageId = R.drawable.icons_expert, cardText = Constants.EXPERT, clickHomeItem = { clickHomeItem.invoke(Constants.EXPERT) }, backgroundColor = MaterialTheme.colorScheme.onErrorContainer),
        ScreenItem(imageId = R.drawable.icons_europe, cardText = Constants.EUROPE, clickHomeItem = { clickHomeItem.invoke(Constants.EUROPE) }, backgroundColor = MaterialTheme.colorScheme.outlineVariant),
        ScreenItem(imageId = R.drawable.icon_america, cardText = Constants.AMERICA, clickHomeItem = { clickHomeItem.invoke(Constants.AMERICA) }, backgroundColor = MaterialTheme.colorScheme.secondaryContainer),
        ScreenItem(imageId = R.drawable.icon_africa, cardText = Constants.AFRICA, clickHomeItem = { clickHomeItem.invoke(Constants.AFRICA) }, backgroundColor = MaterialTheme.colorScheme.tertiary),
        ScreenItem(imageId = R.drawable.icon_asia, cardText = Constants.ASIA, clickHomeItem = { clickHomeItem.invoke(Constants.ASIA) }, backgroundColor = MaterialTheme.colorScheme.scrim),
        ScreenItem(imageId = R.drawable.icon_oceania, cardText = Constants.OCEANIA, clickHomeItem = { clickHomeItem.invoke(Constants.OCEANIA) }, backgroundColor = MaterialTheme.colorScheme.outline),
        )

    Column {
        AppBar(
            backButtonCheck = true,
            imageId = R.drawable.icon_app_bar,
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