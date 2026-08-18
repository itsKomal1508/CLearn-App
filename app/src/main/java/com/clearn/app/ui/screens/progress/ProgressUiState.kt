package com.clearn.app.ui.screens.progress

import com.clearn.app.data.local.entity.BookmarkEntity
import com.clearn.app.data.model.DiplomaUnit

data class UnitProgressDetail(
    val unit: DiplomaUnit,
    val completedCount: Int,
    val totalCount: Int,
    val percent: Float
)

data class ProgressUiState(
    val totalTopicsCount: Int = 0,
    val completedTopicsCount: Int = 0,
    val overallPercent: Float = 0f,
    val activeStreak: Int = 1,
    val bestStreak: Int = 1,
    val unitProgressList: List<UnitProgressDetail> = emptyList(),
    val quizzesTakenCount: Int = 0,
    val totalQuizScore: Int = 0,
    val totalQuizQuestions: Int = 0,
    val bookmarks: List<BookmarkEntity> = emptyList()
)
