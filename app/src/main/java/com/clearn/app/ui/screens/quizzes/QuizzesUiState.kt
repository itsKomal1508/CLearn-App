package com.clearn.app.ui.screens.quizzes

import com.clearn.app.data.local.entity.QuizResultEntity
import com.clearn.app.data.model.QuizUnit

data class QuizzesUiState(
    val quizUnits: List<QuizUnit> = emptyList(),
    val quizResultsMap: Map<Int, QuizResultEntity> = emptyMap()
)
