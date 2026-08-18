package com.clearn.app.data.model

data class QuizUnit(
    val unitId: Int,
    val unitNumber: String,
    val unitTitle: String,
    val questions: List<QuizQuestion>
)
