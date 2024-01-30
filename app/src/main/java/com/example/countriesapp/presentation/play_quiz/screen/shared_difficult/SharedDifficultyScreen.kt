package com.example.countriesapp.presentation.play_quiz.screen.shared_difficult

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.countriesapp.R
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.QuizItemCard

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SharedDifficultyScreen(
    difficultLevel:String,
    backClick: () -> Unit,
    chooseCategoryData: (String,String) -> Unit
) {
    val answerOptions: List<String> by remember {
        mutableStateOf(
            listOf(
                "Flag",
                "Capital",
                "Emblems",
            )
        )
    }
    val backgroundColors: List<Color> = remember {
        mutableListOf(Color.Green, Color.LightGray, Color.Cyan, Color.Magenta)
    }

    Column {
        AppBar(
            backButtonCheck = true,
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick.invoke()
            })

        LazyColumn(
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(answerOptions) { index, item ->
                QuizItemCard(
                    quizText = item,
                    clickItem = {
                        chooseCategoryData.invoke(item,difficultLevel)
                    }, backgroundColor = backgroundColors[index]
                )
            }
        }
    }
}