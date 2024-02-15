package com.example.countriesapp.presentation.play_quiz.state

import com.mckstudio.countriesapp.domain.model.QuizItem

data class QuizState(
    val loading : Boolean=false,
    val error : String="",
    var quizData : List<QuizItem> ?= emptyList()
)
