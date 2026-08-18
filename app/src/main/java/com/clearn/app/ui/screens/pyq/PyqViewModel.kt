package com.clearn.app.ui.screens.pyq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clearn.app.data.repository.PyqRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PyqViewModel(
    private val pyqRepository: PyqRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PyqUiState())
    val uiState: StateFlow<PyqUiState> = _uiState.asStateFlow()

    init {
        loadPapers()
    }

    private fun loadPapers() {
        val allPapers = pyqRepository.getPyqPapers()
        _uiState.update {
            it.copy(
                papers = allPapers,
                filteredPapers = allPapers
            )
        }
    }

    fun updateYearFilter(year: String) {
        _uiState.update { state ->
            val filtered = filterPapers(state.papers, year, state.searchQuery)
            state.copy(
                selectedYearFilter = year,
                filteredPapers = filtered
            )
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { state ->
            val filtered = filterPapers(state.papers, state.selectedYearFilter, query)
            state.copy(
                searchQuery = query,
                filteredPapers = filtered
            )
        }
    }

    private fun filterPapers(all: List<com.clearn.app.data.model.PyqPaper>, year: String, query: String): List<com.clearn.app.data.model.PyqPaper> {
        return all.filter { paper ->
            val matchesYear = if (year == "All") true else paper.year == year
            val matchesQuery = if (query.isEmpty()) true else {
                paper.title.contains(query, ignoreCase = true) ||
                paper.examSession.contains(query, ignoreCase = true)
            }
            matchesYear && matchesQuery
        }
    }

    class Factory(
        private val pyqRepository: PyqRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PyqViewModel(pyqRepository) as T
        }
    }
}
