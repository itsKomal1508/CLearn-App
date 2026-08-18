package com.clearn.app.ui.screens.lessondetail

import com.clearn.app.data.model.MicroLesson

data class LessonDetailUiState(
    val unitId: Int = 0,
    val topicId: String = "",
    val topicTitle: String = "",
    val unitTitle: String = "",
    val microLesson: MicroLesson? = null,
    val isCompleted: Boolean = false,
    val isBookmarked: Boolean = false,
    val isHinglishMode: Boolean = false,
    val nextTopicId: String? = null,
    val prevTopicId: String? = null
)
