package com.clearn.app.ui.screens.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clearn.app.data.repository.SyllabusRepository
import com.clearn.app.data.repository.UserProgressRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ProgressViewModel(
    private val syllabusRepository: SyllabusRepository,
    private val userProgressRepository: UserProgressRepository
) : ViewModel() {

    val uiState: StateFlow<ProgressUiState> = combine(
        userProgressRepository.getAllProgress(),
        userProgressRepository.getAllQuizResults(),
        userProgressRepository.getStreak(),
        userProgressRepository.getAllBookmarks()
    ) { progressList, quizResults, streakEntity, bookmarksList ->

        val units = syllabusRepository.getDiplomaUnits()
        val completedTopicIds = progressList.filter { it.isCompleted }.map { it.topicId }.toSet()

        var totalTopics = 0
        var completedTopics = 0

        val unitDetails = units.map { unit ->
            val totalInUnit = unit.topics.size
            val completedInUnit = unit.topics.count { completedTopicIds.contains(it.id) }

            totalTopics += totalInUnit
            completedTopics += completedInUnit

            val pct = if (totalInUnit > 0) completedInUnit.toFloat() / totalInUnit else 0f
            UnitProgressDetail(
                unit = unit,
                completedCount = completedInUnit,
                totalCount = totalInUnit,
                percent = pct
            )
        }

        val overallPct = if (totalTopics > 0) completedTopics.toFloat() / totalTopics else 0f
        val currentStreak = streakEntity?.currentStreak ?: 1
        val bestStreak = streakEntity?.bestStreak ?: 1

        val quizzesTaken = quizResults.size
        val quizScoreSum = quizResults.sumOf { it.score }
        val quizQuestionSum = quizResults.sumOf { it.totalQuestions }

        ProgressUiState(
            totalTopicsCount = totalTopics,
            completedTopicsCount = completedTopics,
            overallPercent = overallPct,
            activeStreak = currentStreak,
            bestStreak = bestStreak,
            unitProgressList = unitDetails,
            quizzesTakenCount = quizzesTaken,
            totalQuizScore = quizScoreSum,
            totalQuizQuestions = quizQuestionSum,
            bookmarks = bookmarksList
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProgressUiState()
    )

    class Factory(
        private val syllabusRepository: SyllabusRepository,
        private val userProgressRepository: UserProgressRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProgressViewModel(syllabusRepository, userProgressRepository) as T
        }
    }
}
