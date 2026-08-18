package com.clearn.app.data.model

data class Topic(
    val id: String,
    val unitId: Int,
    val title: String,
    val estimatedMinutes: Int,
    val microLesson: MicroLesson
)
