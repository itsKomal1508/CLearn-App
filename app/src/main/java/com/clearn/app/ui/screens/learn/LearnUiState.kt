package com.clearn.app.ui.screens.learn

import com.clearn.app.data.model.DiplomaUnit
import com.clearn.app.data.model.Topic

data class LearnUiState(
    val units: List<DiplomaUnit> = emptyList(),
    val completedTopicIds: Set<String> = emptySet(),
    val overallProgressPercent: Float = 0f,
    val totalTopicsCount: Int = 0,
    val completedTopicsCount: Int = 0,
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val searchResults: List<Topic> = emptyList()
)
