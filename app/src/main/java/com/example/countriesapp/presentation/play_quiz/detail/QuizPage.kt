package com.example.countriesapp.presentation.play_quiz.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.countriesapp.R
import com.example.countriesapp.domain.model.QuizItem
import com.example.countriesapp.layouts.AppBar


@Composable
fun QuizPage(
    backClick: () -> Unit,
    //quizList: List<QuizItem>
) {
    val newList = listOf(
        QuizItem(R.drawable.icons_turkey,"Turkey"),
        QuizItem(R.drawable.icons_europe,"Europe"),
        QuizItem(R.drawable.icon_asia,"Asia"),
        QuizItem(R.drawable.icon_africa,"Africa"),
        QuizItem(R.drawable.icon_america,"America"),
        QuizItem(R.drawable.icons_brazil,"Brazil"),
        QuizItem(R.drawable.icon_oceania,"Ocean"),
        QuizItem(R.drawable.icons_antarctic,"Antart")
    )
    var sliderState by remember { mutableFloatStateOf(0f) }

    var correctAnswerIndex by remember { mutableIntStateOf(0) }

    var currentQuizQuestion by remember { mutableStateOf(newList[correctAnswerIndex]) }

    var checkAnswerString by remember { mutableStateOf(currentQuizQuestion.name) }

    var otherOptions = newList.shuffled().take(3).map { it.name }

    var answerOptions = listOf(checkAnswerString) + otherOptions.shuffled()

    Column {
        AppBar(
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick.invoke()
            })
        SeekBar(sliderSituation = sliderState)

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(contentPadding = PaddingValues(10.dp)) {
                item {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp)
                            .height(250.dp),
                        model = currentQuizQuestion.flag, contentDescription = ""
                    )
                }

                answerOptions.shuffled().forEach { answerText ->
                    item {
                        if (answerText != null) {
                            AnswerButton(text = answerText, onClick = {
                                if (checkAnswerString == answerText) {
                                    correctAnswerIndex++
                                    sliderState +=1f

                                    if (correctAnswerIndex < newList.size) {
                                        currentQuizQuestion = newList[correctAnswerIndex]
                                        checkAnswerString = currentQuizQuestion.name

                                        otherOptions = newList.shuffled().take(3).map { it.name }

                                        answerOptions = listOf(checkAnswerString) + otherOptions.shuffled()

                                    } else {
                                        //Quiz doğru cevab verince burada bitiyor.
                                        println("Quiz bitti, yeni bir şey yapabilirsiniz.")
                                    }
                                } else {
                                    //Yanlış cevab
                                    println("NO")
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SeekBar(
    sliderSize: Float = 0f,
    sliderSituation: Float = 0f
) {
    var sliderState by remember { mutableFloatStateOf(sliderSituation) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderState,
            onValueChange = { newValue ->
                sliderState = newValue
            },
            valueRange = 0f..sliderSize,
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.Gray)
                .padding(vertical = 8.dp)
        )
    }
}

@Composable
private fun AnswerButton(
    text: String,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(50.dp)
            .clickable { onClick(text) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray,
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

//lazycolumun içi
//item {
//                    AsyncImage(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(25.dp)
//                            .height(250.dp),
//                        model = R.drawable.ic_launcher_background, contentDescription = ""
//                    )
//                }
//
//                // Şıkları gösteren AnswerButton'ları oluştur
//                answerOptions.forEach { answerText ->
//                    item {
//                        if (answerText != null) {
//                            AnswerButton(text = answerText, onClick = {
//                                if (checkAnswerString == answerText) {
//                                    // Doğru cevap
//                                } else {
//                                    // Yanlış cevap
//                                }
//                            })
//                        }
//                    }
//                }
