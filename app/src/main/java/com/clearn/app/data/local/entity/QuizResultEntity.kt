package com.clearn.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_results")
data class QuizResultEntity(
    @PrimaryKey
    val unitId: Int,
    val score: Int,
    val totalQuestions: Int,
    val passed: Boolean,
    val attemptedAt: Long = System.currentTimeMillis()
)
