package com.clearn.app.data.model

data class QuizQuestion(
    val id: String,
    val unitId: Int,
    val questionText: String,
    val codeSnippet: String? = null,
    val options: List<String>,
    val correctOptionIndex: Int,
    val explanation: String
)
