package com.clearn.app.data.model

data class PyqPaper(
    val id: String,
    val title: String,
    val examSession: String, // e.g. "Summer 2024"
    val subjectCode: String = "312303",
    val year: String, // "2024"
    val questionPaperPdfUrl: String,
    val modelAnswerPdfUrl: String,
    val fileSize: String = "1.4 MB"
)
