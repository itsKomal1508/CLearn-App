package com.clearn.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    // Bottom Nav Tabs
    object Learn : Screen("learn", "Learn", Icons.Default.Book)
    object Playground : Screen("playground", "Playground", Icons.Default.Code)
    object Quizzes : Screen("quizzes", "Quizzes", Icons.Default.Quiz)
    object Pyq : Screen("pyq", "PYQ", Icons.Default.Description)
    object Progress : Screen("progress", "Progress", Icons.Default.BarChart)

    // Secondary / Detail Navigation Routes
    object LessonDetail : Screen("lesson_detail/{unitId}/{topicId}", "Lesson Detail") {
        fun createRoute(unitId: Int, topicId: String) = "lesson_detail/$unitId/$topicId"
    }

    object QuizRunner : Screen("quiz_runner/{unitId}", "Quiz Runner") {
        fun createRoute(unitId: Int) = "quiz_runner/$unitId"
    }

    companion object {
        val bottomNavItems = listOf(Learn, Playground, Quizzes, Pyq, Progress)
    }
}
