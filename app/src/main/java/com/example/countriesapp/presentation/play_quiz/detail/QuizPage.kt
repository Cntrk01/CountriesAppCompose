package com.example.countriesapp.presentation.play_quiz.detail

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
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
        QuizItem(R.drawable.icons_antarctic, "Antartica")
    )
    var sliderState by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()
    var isFinished by remember { mutableStateOf(true) }

    var correctAnswerIndex by remember { mutableIntStateOf(0) }
    var correctAnswerLastIndex by remember { mutableIntStateOf(-1) }

    var currentQuizQuestion by remember { mutableStateOf<QuizItem?>(null) }
    var checkAnswerString by remember { mutableStateOf<String?>(null) }
    var currentQuizQuestionFlag by remember { mutableStateOf<Int>(0) }
    var userCheckWrongAnswer by remember { mutableIntStateOf(2) }

    var answerOptions: List<String?> by remember { mutableStateOf(emptyList()) }
    val otherOptions = newList.shuffled().take(3).map { it.name }.toMutableList()

    //LaunchedEffect tek seferlik işlemler için RememberCoroutineScope ise uzun ömürlü işlemler içindir
    //LaunchedEffect, Composable yaşam döngüsüne sıkı sıkıya bağlı bir Composable işlevidir; RememberCoroutineScope ise Composable işlevlerinin dışında kullanılabilir
    //Burada LaunchedEffect kullanamayız.Çünkü her yeni gelecek soru için kontrol sağlanıp işlem yapması lazım.
    //LaunchedEffect tek sefer çalışıp bırakıyor
    if (isFinished){
        coroutineScope.launch {
            if (correctAnswerIndex != 0){
                delay(1000)
            }
            if (correctAnswerLastIndex != correctAnswerIndex) {
                for (i in otherOptions.indices) {
                    var newItem: String

                    do {
                        newItem = newList.shuffled().take(1).map { it.name }.first().toString()
                    }while (correctAnswerIndex < newList.size && newItem == newList[correctAnswerIndex].name || otherOptions.contains(newItem))

                    otherOptions[i] = newItem
                }
                correctAnswerLastIndex = correctAnswerIndex
                answerOptions = (listOf(newList[correctAnswerIndex].name) + otherOptions.shuffled()).shuffled()

                currentQuizQuestion = newList[correctAnswerIndex]
                checkAnswerString = currentQuizQuestion?.name
                currentQuizQuestionFlag= currentQuizQuestion?.flag!!
            }
        }
    }

    Column {
        AppBar(
            backButtonCheck = true,
            imageId = R.drawable.icons_turkey,
            backClick = {
                //backbuton için de basınca bir uyarı popup açmalıyız.
                backClick.invoke()
            })
        SeekBar(sliderSize = newList.size.toFloat(), sliderSituation = sliderState)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$correctAnswerIndex /", fontSize = 16.sp)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "${newList.size}",fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(30.dp))

        CheckUserWrongQuestionBar(userCheckWrongAnswer = userCheckWrongAnswer)

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(contentPadding = PaddingValues(10.dp)) {
                item {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .height(250.dp),
                        model = currentQuizQuestionFlag, contentDescription = "",
                        contentScale = ContentScale.Crop,
                        alignment= Alignment.Center
                    )
                }

                answerOptions.forEach { answerText ->
                    item {
                        if (answerText != null) {
                            AnswerButton(text = answerText, onClick = {
                                if (userCheckWrongAnswer != -1) {
                                    if (correctAnswerIndex != newList.size) {
                                        if (checkAnswerString == answerText) {
                                            correctAnswerIndex++
                                            sliderState += 1f

                                            println(correctAnswerIndex)
                                            if (correctAnswerIndex < newList.size) {
                                                currentQuizQuestion = newList[correctAnswerIndex]
                                                checkAnswerString = currentQuizQuestion?.name
                                            } else if (correctAnswerIndex == newList.size) {
                                                //son indexe geldiğinde tekrar döngüye girmemesini engelliyorum .
                                                isFinished=false
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
                            }, correctAnswer = checkAnswerString.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CheckUserWrongQuestionBar(userCheckWrongAnswer: Int) {
    val heartIcon = painterResource(id = R.drawable.icon_hearth)
    val brokenHeartIcon = painterResource(id = R.drawable.icons_broken_heart)

    val icons = when (userCheckWrongAnswer) {
        2 -> listOf(heartIcon, heartIcon, heartIcon)
        1 -> listOf(heartIcon, heartIcon)
        0 -> listOf(heartIcon)
        -1 -> listOf(brokenHeartIcon)
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

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            progress = sliderSituation / sliderSize,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            color = Color.Green,
        )
    }
}

@Composable
private fun AnswerButton(
    text: String,
    onClick: (String) -> Unit,
    correctAnswer: String,
) {
    var backgroundColor by remember { mutableStateOf(Color.Gray) }
    val coroutineScope= rememberCoroutineScope()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(50.dp)
            .clickable {
                onClick(text)
                if (text == correctAnswer) {
                    backgroundColor = Color.Green

                    coroutineScope.launch {
                        delay(500)
                        backgroundColor = Color.Gray
                    }
                } else {
                    backgroundColor = Color.Red

                    coroutineScope.launch {
                        delay(500)
                        backgroundColor = Color.Gray
                    }
                }
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
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
