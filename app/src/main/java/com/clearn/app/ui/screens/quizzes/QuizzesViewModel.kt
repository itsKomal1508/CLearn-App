package com.clearn.app.ui.screens.quizzes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clearn.app.data.repository.QuizRepository
import com.clearn.app.data.repository.UserProgressRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class QuizzesViewModel(
    private val quizRepository: QuizRepository,
    private val userProgressRepository: UserProgressRepository
) : ViewModel() {

    val uiState: StateFlow<QuizzesUiState> = userProgressRepository.getAllQuizResults()
        .map { resultsList ->
            val resultMap = resultsList.associateBy { it.unitId }
            QuizzesUiState(
                quizUnits = quizRepository.getQuizUnits(),
                quizResultsMap = resultMap
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = QuizzesUiState(quizUnits = quizRepository.getQuizUnits())
        )

    class Factory(
        private val quizRepository: QuizRepository,
        private val userProgressRepository: UserProgressRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return QuizzesViewModel(quizRepository, userProgressRepository) as T
        }
    }
}
