package com.clearn.app.ui.screens.quizzes

import com.clearn.app.data.model.QuizQuestion

data class QuizRunnerUiState(
    val unitId: Int = 0,
    val unitTitle: String = "",
    val questions: List<QuizQuestion> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val score: Int = 0,
    val isQuizFinished: Boolean = false
)
