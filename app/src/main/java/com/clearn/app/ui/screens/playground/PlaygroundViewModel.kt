package com.clearn.app.ui.screens.playground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.clearn.app.data.analytics.AnalyticsHelper
import com.clearn.app.data.repository.ExecutionResult
import com.clearn.app.data.repository.PlaygroundRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlaygroundViewModel(
    private val playgroundRepository: PlaygroundRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlaygroundUiState())
    val uiState: StateFlow<PlaygroundUiState> = _uiState.asStateFlow()

    fun updateCode(newCode: String) {
        _uiState.update { it.copy(code = newCode) }
    }

    fun updateInput(newInput: String) {
        _uiState.update { it.copy(input = newInput) }
    }

    fun loadTemplate(templateCode: String) {
        _uiState.update {
            it.copy(
                code = templateCode,
                outputText = null,
                isError = false
            )
        }
    }

    fun clearConsole() {
        _uiState.update { it.copy(outputText = null, isError = false) }
    }

    fun runCode() {
        val currentCode = _uiState.value.code
        val currentInput = _uiState.value.input.ifEmpty { null }

        _uiState.update { it.copy(isRunning = true, outputText = null, isError = false) }

        viewModelScope.launch {
            val result = playgroundRepository.executeCCode(currentCode, currentInput)
            _uiState.update { state ->
                when (result) {
                    is ExecutionResult.Success -> {
                        AnalyticsHelper.logCodeRunAttempted(success = true)
                        state.copy(
                            isRunning = false,
                            outputText = result.stdout,
                            isError = false,
                            executionTime = result.time,
                            memoryUsage = result.memory
                        )
                    }
                    is ExecutionResult.Error -> {
                        AnalyticsHelper.logCodeRunAttempted(success = false)
                        state.copy(
                            isRunning = false,
                            outputText = result.friendlyErrorMessage,
                            isError = true,
                            executionTime = null,
                            memoryUsage = null
                        )
                    }
                }
            }
        }
    }

    class Factory(
        private val playgroundRepository: PlaygroundRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PlaygroundViewModel(playgroundRepository) as T
        }
    }
}
