package com.clearn.app.ui.screens.pyq

import com.clearn.app.data.model.PyqPaper

data class PyqUiState(
    val papers: List<PyqPaper> = emptyList(),
    val filteredPapers: List<PyqPaper> = emptyList(),
    val selectedYearFilter: String = "All",
    val searchQuery: String = ""
)
