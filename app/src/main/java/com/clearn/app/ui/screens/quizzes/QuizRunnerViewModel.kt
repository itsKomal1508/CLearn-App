package com.clearn.app.ui.screens.quizzes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clearn.app.data.analytics.AnalyticsHelper
import com.clearn.app.data.repository.QuizRepository
import com.clearn.app.data.repository.UserProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizRunnerViewModel(
    private val unitId: Int,
    private val quizRepository: QuizRepository,
    private val userProgressRepository: UserProgressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizRunnerUiState(unitId = unitId))
    val uiState: StateFlow<QuizRunnerUiState> = _uiState.asStateFlow()

    init {
        loadQuizUnit()
    }

    private fun loadQuizUnit() {
        val quizUnit = quizRepository.getQuizUnitById(unitId)
        if (quizUnit != null) {
            _uiState.update {
                it.copy(
                    unitTitle = quizUnit.unitTitle,
                    questions = quizUnit.questions
                )
            }
        }
    }

    fun selectOption(optionIndex: Int) {
        if (_uiState.value.isAnswerSubmitted) return

        _uiState.update { state ->
            val currentQuestion = state.questions[state.currentQuestionIndex]
            val isCorrect = optionIndex == currentQuestion.correctOptionIndex
            val newScore = if (isCorrect) state.score + 1 else state.score

            state.copy(
                selectedOptionIndex = optionIndex,
                isAnswerSubmitted = true,
                score = newScore
            )
        }
    }

    fun nextQuestion() {
        val currentState = _uiState.value
        val nextIndex = currentState.currentQuestionIndex + 1

        if (nextIndex < currentState.questions.size) {
            _uiState.update {
                it.copy(
                    currentQuestionIndex = nextIndex,
                    selectedOptionIndex = null,
                    isAnswerSubmitted = false
                )
            }
        } else {
            val totalQuestions = currentState.questions.size
            val passed = currentState.score >= (totalQuestions * 0.5f)

            viewModelScope.launch {
                userProgressRepository.saveQuizResult(
                    unitId = unitId,
                    score = currentState.score,
                    totalQuestions = totalQuestions,
                    passed = passed
                )
                AnalyticsHelper.logQuizSubmitted(
                    unitId = unitId,
                    score = currentState.score,
                    totalQuestions = totalQuestions,
                    passed = passed
                )
            }

            _uiState.update { it.copy(isQuizFinished = true) }
        }
    }

    class Factory(
        private val unitId: Int,
        private val quizRepository: QuizRepository,
        private val userProgressRepository: UserProgressRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return QuizRunnerViewModel(unitId, quizRepository, userProgressRepository) as T
        }
    }
}
