package com.example.countriesapp.presentation.play_quiz.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.countriesapp.R
import com.example.countriesapp.domain.model.QuizItem
import com.example.countriesapp.layouts.AppBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun QuizPage(
    backClick: () -> Unit,
    //quizList: List<QuizItem>
) {
    val newList = listOf(
        QuizItem(R.drawable.icons_turkey, "Turkey"),
        QuizItem(R.drawable.icons_europe, "Europe"),
        QuizItem(R.drawable.icon_asia, "Asia"),
        QuizItem(R.drawable.icon_africa, "Africa"),
        QuizItem(R.drawable.icon_america, "America"),
        QuizItem(R.drawable.icons_brazil, "Brazil"),
        QuizItem(R.drawable.icon_oceania, "Ocean"),
        QuizItem(R.drawable.icons_antarctic, "Antart")
    )
    var sliderState by remember { mutableFloatStateOf(0f) }

    var correctAnswerIndex by remember { mutableIntStateOf(0) }
    var correctAnswerLastIndex by remember { mutableIntStateOf(-1) }

    var currentQuizQuestion by remember { mutableStateOf(newList[correctAnswerIndex]) }
    var checkAnswerString by remember { mutableStateOf(currentQuizQuestion.name) }

    var userCheckWrongAnswer by remember { mutableIntStateOf(2) }

    var answerOptions : List<String?> by remember { mutableStateOf(emptyList()) }
    val otherOptions = newList.shuffled().take(3).map { it.name }.toMutableList()

    if (correctAnswerLastIndex != correctAnswerIndex){
        for (i in otherOptions.indices) {
            var newItem: String

            do {
                newItem = newList.shuffled().take(1).map { it.name }.first().toString()
            } while (newItem == checkAnswerString || otherOptions.contains(newItem))

            otherOptions[i] = newItem
        }
        correctAnswerLastIndex=correctAnswerIndex
        answerOptions =(listOf(checkAnswerString) + otherOptions.shuffled()).shuffled()
    }

    Column {
        AppBar(
            imageId = R.drawable.icons_turkey,
            backClick = {
                backClick.invoke()
            })
        SeekBar(sliderSituation = sliderState)

        Spacer(modifier = Modifier.height(15.dp))

        CheckUserWrongQuestionBar(userCheckWrongAnswer=userCheckWrongAnswer)

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(contentPadding = PaddingValues(10.dp)) {
                item {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .height(250.dp),
                        model = currentQuizQuestion.flag, contentDescription = ""
                    )
                }

                answerOptions.forEach { answerText ->
                    item {
                        if (answerText != null) {
                            AnswerButton(text = answerText, onClick = {
                                if (userCheckWrongAnswer != -1) {
                                    if (correctAnswerIndex != newList.size){
                                        if (checkAnswerString == answerText) {
                                            correctAnswerIndex++
                                            sliderState += 1f

                                            println(correctAnswerIndex)
                                            if (correctAnswerIndex < newList.size) {
                                                currentQuizQuestion = newList[correctAnswerIndex]
                                                checkAnswerString = currentQuizQuestion.name
                                                println("ASDASDDSDA")
                                                //otherOptions = newList.shuffled().take(3).map { it.name }.toMutableList()
                                                //answerOptions = listOf(checkAnswerString) + otherOptions
                                            } else if (correctAnswerIndex == newList.size) {
                                                //Quiz doğru cevab verince burada bitiyor.
                                                println("Quiz bitti, yeni bir şey yapabilirsiniz.")
                                            }
                                        } else {
                                            userCheckWrongAnswer--
                                            println("No")
                                        }
                                    }
                                } else {
                                    println("Oyun bitti, başka bir şey yapabilirsiniz.")
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
private fun CheckUserWrongQuestionBar(userCheckWrongAnswer:Int){
    val heartIcon = painterResource(id = R.drawable.icon_hearth)
    val brokenHeartIcon = painterResource(id = R.drawable.icons_broken_heart)

    val icons = when (userCheckWrongAnswer) {
        2 -> listOf(heartIcon, heartIcon, heartIcon)
        1 -> listOf(heartIcon, heartIcon)
        0 -> listOf(heartIcon)
        -1-> listOf(brokenHeartIcon)
        else -> emptyList()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        icons.forEachIndexed { index, icon ->
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .then(if (index < icons.size - 1) Modifier.padding(end = 10.dp) else Modifier)
            )
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
