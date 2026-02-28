package com.mckstudio.countriesapp.presentation.play_quiz.screen.detail

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mckstudio.countriesapp.common.Constants
import com.mckstudio.countriesapp.common.Constants.DIFFICULT
import com.mckstudio.countriesapp.domain.model.QuizItem
import com.mckstudio.countriesapp.layouts.AlertDialogForBack
import com.mckstudio.countriesapp.layouts.AppBar
import com.mckstudio.countriesapp.layouts.LoadingCardView
import com.mckstudio.countriesapp.navigation.Screen
import com.example.countriesapp.presentation.play_quiz.state.QuizState
import com.example.countriesapp.presentation.play_quiz.viewmodel.QuizViewModel
import com.mckstuido.countriesapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun QuizPage(
    backClick: (String?) -> Unit,
    navController: NavHostController,
    quizViewModel: QuizViewModel = hiltViewModel(),
) {
    val countryItem =
        remember { navController.previousBackStackEntry?.savedStateHandle?.get<String>(Screen.QuizDetailPage.route) }
    val difficultLevel =
        remember { navController.previousBackStackEntry?.savedStateHandle?.get<String>(DIFFICULT) }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = quizViewModel.quizState
    var checkLoading by remember { mutableStateOf(false) }
    var checkErrorMessage by remember { mutableStateOf("") }
    var quizListData by remember { mutableStateOf<List<QuizItem>?>(null) }

    var checkBackClick by remember { mutableStateOf(false) }
    var isFinishedCheck by remember { mutableStateOf(false) }

    //Side effect yazınca saçmaladı.
    LaunchedEffect(Unit) {

        if (difficultLevel == Constants.EASY) {
            if (countryItem == "Flag") {
                quizViewModel.getEasyQuizFlagQuestion()
            }
            if (countryItem == "Capital") {
                quizViewModel.getEasyQuizCapitalQuestion()
            }
            if (countryItem == "Emblems") {
                quizViewModel.getEasyQuizEmblemsQuestion()
            }
        }

        if (difficultLevel == Constants.MEDIUM) {
            if (countryItem == "Flag") {
                quizViewModel.getMediumQuizFlagQuestion()
            }
            if (countryItem == "Capital") {
                quizViewModel.getMediumQuizCapitalQuestion()
            }
            if (countryItem == "Emblems") {
                quizViewModel.getMediumQuizEmblemsQuestion()
            }
        }

        if (difficultLevel == Constants.HARD) {
            if (countryItem == "Flag") {
                quizViewModel.getHardQuizFlagQuestion()
            }
            if (countryItem == "Capital") {
                quizViewModel.getHardQuizCapitalQuestion()
            }
            if (countryItem == "Emblems") {
                quizViewModel.getHardQuizEmblemsQuestion()
            }
        }

        if (difficultLevel == Constants.EXPERT) {
            if (countryItem == "Flag") {
                quizViewModel.getExpertQuizFlagQuestion()
            }
            if (countryItem == "Capital") {
                quizViewModel.getExpertQuizCapitalQuestion()
            }
            if (countryItem == "Emblems") {
                quizViewModel.getExpertQuizEmblemsQuestion()
            }
        }

        if (difficultLevel == Constants.EUROPE) {
            quizViewModel.getEuropeCountryQuizQuestion()
        }
        if (difficultLevel == Constants.AMERICA) {
            quizViewModel.getAmericaCountryQuizQuestion()
        }
        if (difficultLevel == Constants.AFRICA) {
            quizViewModel.getAfricaCountryQuizQuestion()
        }

        if (difficultLevel == Constants.ASIA) {
            quizViewModel.getAsiaCountryQuizQuestion()
        }
        if (difficultLevel == Constants.OCEANIA) {
            quizViewModel.getOceaniaCountryQuizQuestion()
        }
        stateCollect(
            coroutineScope = coroutineScope,
            state = state,
            checkErrorMessage = {
                checkErrorMessage = it
            },
            quizListData = {
                quizListData = it
            },
            checkLoading = {
                checkLoading = it
            }
        )
    }

    Column {
        AppBar(
            imageId = R.drawable.icon_app_bar,
            backClick = {
                checkBackClick = true
            })

        if (checkBackClick) {
            AlertDialogForBack(
                confirmButton = {
                    checkBackClick = false
                }, dismissButton = {
                    backClick.invoke(difficultLevel)
                })
        }

        if (checkLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                LoadingCardView(modifier = Modifier.align(Center))
            }
        }

        if (checkErrorMessage.isNotEmpty()) {
            Text(text = checkErrorMessage)
        }

        if (quizListData?.isNotEmpty() == true) {
            quizListData?.let { newList1 ->
                var sliderState by remember { mutableFloatStateOf(0f) }
                var isFinished by remember { mutableStateOf(true) }

                var correctAnswerIndex by remember { mutableIntStateOf(0) }
                var correctAnswerLastIndex by remember { mutableIntStateOf(-1) }

                var currentQuizQuestion by remember { mutableStateOf<QuizItem?>(null) }
                var checkAnswerString by remember { mutableStateOf<String?>(null) }
                var currentQuizQuestionFlag by remember { mutableStateOf("") }
                var userCheckWrongAnswer by remember { mutableIntStateOf(2) }

                var answerOptions: List<String?> by remember { mutableStateOf(emptyList()) }
                val otherOptions = newList1.shuffled().take(3).map { it.name }.toMutableList()

                //LaunchedEffect tek seferlik işlemler için RememberCoroutineScope ise uzun ömürlü işlemler içindir
                //LaunchedEffect, Composable yaşam döngüsüne sıkı sıkıya bağlı bir Composable işlevidir; RememberCoroutineScope ise Composable işlevlerinin dışında kullanılabilir
                //Burada LaunchedEffect kullanamayız.Çünkü her yeni gelecek soru için kontrol sağlanıp işlem yapması lazım.
                //LaunchedEffect tek sefer çalışıp bırakıyor
                if (isFinished) {
                    coroutineScope.launch {
                        if (correctAnswerIndex != 0) {
                            delay(1000)
                        }
                        if (correctAnswerLastIndex != correctAnswerIndex) {
                            for (i in otherOptions.indices) {
                                var newItem: String

                                do {
                                    newItem = newList1.shuffled().take(1).map { it.name }.first().toString()
                                } while (correctAnswerIndex < newList1.size && newItem ==
                                    newList1[correctAnswerIndex].name || otherOptions.contains(newItem)
                                )
                                otherOptions[i] = newItem
                            }
                            correctAnswerLastIndex = correctAnswerIndex
                            answerOptions =
                                (listOf(newList1[correctAnswerIndex].name) + otherOptions.shuffled()).shuffled()

                            currentQuizQuestion = newList1[correctAnswerIndex]
                            checkAnswerString = currentQuizQuestion?.name
                            currentQuizQuestionFlag = currentQuizQuestion?.flag!!
                        }
                    }
                }

                SeekBar(sliderSize = newList1.size.toFloat(), sliderSituation = sliderState)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            top = 10.dp
                        ),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = CenterVertically
                ) {
                    Text(
                        text = "$correctAnswerIndex /",
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "${newList1.size}",
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                CheckUserWrongQuestionBar(userCheckWrongAnswer = userCheckWrongAnswer)

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(contentPadding = PaddingValues(10.dp)) {
                        item {
                            Card(
                                modifier = Modifier
                                    .height(250.dp)
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                shape = RoundedCornerShape(10.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    model = currentQuizQuestionFlag, contentDescription = "",
                                    contentScale = ContentScale.FillBounds,
                                    alignment = Center
                                )
                            }
                        }

                        answerOptions.forEach { answerText ->
                            item {
                                if (answerText != null) {
                                    AnswerButton(text = answerText, onClick = {
                                        if (userCheckWrongAnswer != -1) {
                                            if (correctAnswerIndex != newList1.size) {
                                                if (checkAnswerString == answerText) {
                                                    correctAnswerIndex++
                                                    sliderState += 1f

                                                    if (correctAnswerIndex < newList1.size) {
                                                        currentQuizQuestion = newList1[correctAnswerIndex]
                                                        checkAnswerString = currentQuizQuestion?.name
                                                    } else if (correctAnswerIndex == newList1.size) {
                                                        //son indexe geldiğinde tekrar döngüye girmemesini engelliyorum .
                                                        backClick.invoke(difficultLevel)
                                                        Toast.makeText(context, R.string.examCompleted,Toast.LENGTH_LONG).show()
                                                        isFinished = false
                                                    }
                                                } else {
                                                    userCheckWrongAnswer--
                                                }
                                            }
                                        } else {
                                            isFinishedCheck = true
                                        }
                                    }, correctAnswer = checkAnswerString.toString())
                                }
                            }
                        }
                    }
                }
                if (userCheckWrongAnswer == -1) {
                    AlertDialogWithCountdown(
                        confirmButton = {
                            userCheckWrongAnswer += 3
                        }, dismissButton = {
                            backClick.invoke(difficultLevel)
                        })
                }
            }
        }
    }
}

private fun stateCollect(
    coroutineScope: CoroutineScope,
    state: StateFlow<QuizState>,
    checkLoading: (Boolean) -> Unit,
    checkErrorMessage: (String) -> Unit,
    quizListData: (List<QuizItem>) -> Unit
) {
    coroutineScope.launch {
        state.collect { collect ->
            if (collect.loading) {
                checkLoading.invoke(true)
            } else if (collect.error.isNotEmpty()) {
                checkLoading.invoke(false)
                checkErrorMessage.invoke(collect.error)
            } else {
                checkLoading.invoke(false)
                collect.quizData?.shuffled()?.let { quizListData.invoke(it) }
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
            .height(30.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = CenterVertically
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
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(50.dp)
            .clickable (
                indication = null,
                interactionSource = null,
            ) {
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
            contentAlignment = Center
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

@Composable
fun CountdownTimer(
    seconds: Int,
    onCountdownFinished: () -> Unit
) {
    var remainingSeconds by remember { mutableIntStateOf(seconds) }

    LaunchedEffect(key1 = remainingSeconds) {
        while (remainingSeconds > 0) {
            delay(1000)
            remainingSeconds--
        }
        onCountdownFinished.invoke()
    }
    Text(text = "$remainingSeconds", fontSize = 18.sp)
}

@Composable
fun AlertDialogWithCountdown(
    confirmButton: () -> Unit,
    dismissButton: () -> Unit
) {
    val countdownValue by remember { mutableIntStateOf(5) }

    AlertDialog(
        onDismissRequest = {
            dismissButton.invoke()
        },
        confirmButton = {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
                    .clickable (
                        indication = null,
                        interactionSource = null,
                    ) {
                        confirmButton.invoke()
                    },
                painter = painterResource(id = R.drawable.icons_watch_ads),
                contentDescription = ""
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.you_have_no_rights_left_watch_the_video_to_continue),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                CountdownTimer(
                    seconds = countdownValue,
                    onCountdownFinished = {
                        dismissButton.invoke()
                    }
                )
            }
        },
    )
}