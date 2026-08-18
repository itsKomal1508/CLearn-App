package com.clearn.app.ui.screens.lessondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clearn.app.data.analytics.AnalyticsHelper
import com.clearn.app.data.repository.SyllabusRepository
import com.clearn.app.data.repository.UserProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LessonDetailViewModel(
    private val unitId: Int,
    private val topicId: String,
    private val syllabusRepository: SyllabusRepository,
    private val userProgressRepository: UserProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LessonDetailUiState(unitId = unitId, topicId = topicId))
    val uiState: StateFlow<LessonDetailUiState> = _uiState.asStateFlow()

    init {
        loadTopicData()
    }

    private fun loadTopicData() {
        val unit = syllabusRepository.getUnitById(unitId)
        val topic = syllabusRepository.getTopicById(unitId, topicId)

        if (unit != null && topic != null) {
            val topicsInUnit = unit.topics
            val currentIndex = topicsInUnit.indexOfFirst { it.id == topicId }
            val nextId = if (currentIndex != -1 && currentIndex < topicsInUnit.size - 1) topicsInUnit[currentIndex + 1].id else null
            val prevId = if (currentIndex > 0) topicsInUnit[currentIndex - 1].id else null

            viewModelScope.launch {
                combine(
                    userProgressRepository.getProgressForTopic(topicId),
                    userProgressRepository.isBookmarked(topicId)
                ) { progress, bookmarked ->
                    Pair(progress?.isCompleted == true, bookmarked)
                }.collect { (completed, bookmarked) ->
                    _uiState.update {
                        it.copy(
                            unitTitle = unit.title,
                            topicTitle = topic.title,
                            microLesson = topic.microLesson,
                            isCompleted = completed,
                            isBookmarked = bookmarked,
                            nextTopicId = nextId,
                            prevTopicId = prevId
                        )
                    }
                }
            }
        }
    }

    fun toggleLanguageMode() {
        _uiState.update { it.copy(isHinglishMode = !it.isHinglishMode) }
    }

    fun markCompleted() {
        viewModelScope.launch {
            userProgressRepository.markTopicCompleted(topicId, unitId)
            AnalyticsHelper.logLessonCompleted(topicId, unitId)
        }
    }

    fun toggleBookmark() {
        val currentState = _uiState.value
        viewModelScope.launch {
            userProgressRepository.toggleBookmark(
                topicId = topicId,
                unitId = unitId,
                topicTitle = currentState.topicTitle,
                currentlyBookmarked = currentState.isBookmarked
            )
        }
    }

    class Factory(
        private val unitId: Int,
        private val topicId: String,
        private val syllabusRepository: SyllabusRepository,
        private val userProgressRepository: UserProgressRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LessonDetailViewModel(unitId, topicId, syllabusRepository, userProgressRepository) as T
        }
    }
}
