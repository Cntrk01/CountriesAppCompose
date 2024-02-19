package com.mckstudio.countriesapp.presentation.play_quiz.screen.shared_difficult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mckstudio.countriesapp.common.Constants
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.QuizItemCard
import com.mckstuido.countriesapp.R

@Composable
fun SharedDifficultyScreen(
    difficultLevel:String,
    backClick: () -> Unit,
    chooseCategoryData: (String,String) -> Unit
) {
    val context = LocalContext.current
    val answerOptions =listOf(R.string.flag, R.string.capital, R.string.emblems)

    val backgroundColors: List<Color> = remember {
        mutableListOf(Color.Green, Color.LightGray, Color.Cyan, Color.Magenta)
    }
    //String ifadeleri aldık ama int olarak geliyordu burada getString ile ifadeleri stringe çevirip listede tuttum
    val stringAnswerOptions: List<String> = answerOptions.map { context.getString(it) }

    Column {
        AppBar(
            backButtonCheck = true,
            imageId = R.drawable.icon_app_bar,
            backClick = {
                backClick.invoke()
            })

        LazyColumn(
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(stringAnswerOptions) { index, item ->
                QuizItemCard(
                    quizText = stringAnswerOptions[index],
                    clickItem = {
                        when (index) {
                            0 -> {
                                chooseCategoryData.invoke(Constants.Flag, difficultLevel)
                            }
                            1 -> {
                                chooseCategoryData.invoke(Constants.Capital, difficultLevel)
                            }
                            2 -> {
                                chooseCategoryData.invoke(Constants.Emblems, difficultLevel)
                            }
                        }
                    },
                    backgroundColor = backgroundColors[index]
                )
            }
        }
    }
}