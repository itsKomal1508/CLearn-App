package com.clearn.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_progress")
data class LessonProgressEntity(
    @PrimaryKey
    val topicId: String,
    val unitId: Int,
    val isCompleted: Boolean,
    val completedAt: Long = System.currentTimeMillis()
)
