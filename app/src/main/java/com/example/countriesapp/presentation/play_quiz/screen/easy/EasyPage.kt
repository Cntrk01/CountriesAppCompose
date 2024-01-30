package com.example.countriesapp.presentation.play_quiz.screen.easy

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countriesapp.R
import com.example.countriesapp.domain.model.QuizItem
import com.example.countriesapp.layouts.AppBar
import com.example.countriesapp.layouts.QuizItemCard
import com.example.countriesapp.presentation.play_quiz.viewmodel.QuizViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EasyPage(
    backClick: () -> Unit,
    chooseCategoryData: (String) -> Unit
) {
    val answerOptions: List<String> by remember {
        mutableStateOf(
            listOf(
                "Flag",
                "Country",
                "Capital",
                "Local Flag",
            )
        )
    }
    val backgroundColors: List<Color> = remember {
        mutableListOf(
            Color.Green, Color.LightGray, Color.Cyan,
            Color.Magenta
        )
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
                        chooseCategoryData.invoke(item)
                    }, backgroundColor = backgroundColors[index]
                )
            }
        }
    }
}