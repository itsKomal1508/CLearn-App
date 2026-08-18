package com.clearn.app.data.model

data class DiplomaUnit(
    val id: Int,
    val unitNumber: String,
    val title: String,
    val description: String,
    val topics: List<Topic>
)
