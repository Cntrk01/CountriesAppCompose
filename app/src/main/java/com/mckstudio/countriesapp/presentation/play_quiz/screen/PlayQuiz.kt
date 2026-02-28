package com.mckstudio.countriesapp.presentation.play_quiz.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mckstudio.countriesapp.common.Constants
import com.mckstudio.countriesapp.domain.model.ScreenItem
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.HomeCard
import com.mckstuido.countriesapp.R

@Composable
fun PlayQuiz(
    backClick: () -> Unit,
    clickHomeItem: ((String) -> Unit)
) {
    val quizItems = listOf(
        ScreenItem(imageId = R.drawable.icons_planet_earth, cardText = stringResource(id = R.string.easy), clickHomeItem = { clickHomeItem.invoke(
            Constants.EASY) }, backgroundColor = Color.Green),
        ScreenItem(imageId = R.drawable.icons_medium, cardText = stringResource(id = R.string.medium), clickHomeItem = { clickHomeItem.invoke(
            Constants.MEDIUM) }, backgroundColor = MaterialTheme.colorScheme.inversePrimary),
        ScreenItem(imageId = R.drawable.icons_hard, cardText = stringResource(id = R.string.hard), clickHomeItem = { clickHomeItem.invoke(
            Constants.HARD) }, backgroundColor = Color.Red),
        ScreenItem(imageId = R.drawable.icons_expert, cardText = stringResource(id = R.string.expert), clickHomeItem = { clickHomeItem.invoke(
            Constants.EXPERT) }, backgroundColor = MaterialTheme.colorScheme.onErrorContainer),
        ScreenItem(imageId = R.drawable.icons_europe, cardText = stringResource(id = R.string.europe), clickHomeItem = { clickHomeItem.invoke(
            Constants.EUROPE) }, backgroundColor = MaterialTheme.colorScheme.outlineVariant),
        ScreenItem(imageId = R.drawable.icon_america, cardText = stringResource(id = R.string.america), clickHomeItem = { clickHomeItem.invoke(
            Constants.AMERICA) }, backgroundColor = MaterialTheme.colorScheme.secondaryContainer),
        ScreenItem(imageId = R.drawable.icon_africa, cardText = stringResource(id = R.string.africa), clickHomeItem = { clickHomeItem.invoke(
            Constants.AFRICA) }, backgroundColor = MaterialTheme.colorScheme.tertiary),
        ScreenItem(imageId = R.drawable.icon_asia, cardText = stringResource(id = R.string.asia), clickHomeItem = { clickHomeItem.invoke(
            Constants.ASIA) }, backgroundColor = MaterialTheme.colorScheme.scrim),
        ScreenItem(imageId = R.drawable.icon_oceania, cardText = stringResource(id = R.string.oceania), clickHomeItem = { clickHomeItem.invoke(
            Constants.OCEANIA) }, backgroundColor = MaterialTheme.colorScheme.outline),
        )

    Column {
        AppBar(
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