package com.clearn.app.data.local.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "streaks")
data class StreakEntity(
    @PrimaryKey
    val id: Int = 1, // Single row table storing current streak state
    val currentStreak: Int,
    val lastOpenDate: String, // YYYY-MM-DD format for simple date comparison
    val bestStreak: Int
)