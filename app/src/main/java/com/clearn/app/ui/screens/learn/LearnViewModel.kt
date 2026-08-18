package com.clearn.app.ui.screens.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clearn.app.data.model.Topic
import com.clearn.app.data.repository.SyllabusRepository
import com.clearn.app.data.repository.UserProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LearnViewModel(
    private val syllabusRepository: SyllabusRepository,
    private val userProgressRepository: UserProgressRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _isSearching = MutableStateFlow(false)

    val uiState: StateFlow<LearnUiState> = combine(
        userProgressRepository.getAllProgress(),
        _searchQuery,
        _isSearching
    ) { progressList, query, searching ->
        val completedIds = progressList.filter { it.isCompleted }.map { it.topicId }.toSet()
        val units = syllabusRepository.getDiplomaUnits()

        val totalTopics = units.sumOf { it.topics.size }
        val completedCount = completedIds.size
        val progressPercent = if (totalTopics > 0) completedCount.toFloat() / totalTopics else 0f

        val allTopics = units.flatMap { it.topics }
        val results = if (query.isEmpty()) {
            emptyList()
        } else {
            allTopics.filter { topic ->
                topic.title.contains(query, ignoreCase = true) ||
                topic.microLesson.explanation.contains(query, ignoreCase = true) ||
                (topic.microLesson.codeSnippet?.contains(query, ignoreCase = true) == true)
            }
        }

        LearnUiState(
            units = units,
            completedTopicIds = completedIds,
            overallProgressPercent = progressPercent,
            totalTopicsCount = totalTopics,
            completedTopicsCount = completedCount,
            searchQuery = query,
            isSearching = searching,
            searchResults = results
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = LearnUiState(units = syllabusRepository.getDiplomaUnits())
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleSearchMode() {
        _isSearching.update { !it }
        if (!_isSearching.value) {
            _searchQuery.value = ""
        }
    }

    class Factory(
        private val syllabusRepository: SyllabusRepository,
        private val userProgressRepository: UserProgressRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LearnViewModel(syllabusRepository, userProgressRepository) as T
        }
    }
}
