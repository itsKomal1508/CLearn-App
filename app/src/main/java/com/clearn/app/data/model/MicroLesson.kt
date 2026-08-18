package com.clearn.app.data.model

data class MicroLesson(
    val topicId: String,
    val title: String,
    val explanation: String,
    val hinglishExplanation: String? = null,
    val codeSnippet: String? = null,
    val keyTakeaways: List<String>,
    val animationFlagged: Boolean = false,
    val animationNote: String? = null
)
