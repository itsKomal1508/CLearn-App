package com.clearn.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.clearn.app.CLearnApplication
import com.clearn.app.data.remote.api.RetrofitClient
import com.clearn.app.data.repository.PlaygroundRepository
import com.clearn.app.data.repository.PyqRepository
import com.clearn.app.data.repository.QuizRepository
import com.clearn.app.data.repository.SyllabusRepository
import com.clearn.app.ui.screens.learn.LearnScreen
import com.clearn.app.ui.screens.learn.LearnViewModel
import com.clearn.app.ui.screens.lessondetail.LessonDetailScreen
import com.clearn.app.ui.screens.lessondetail.LessonDetailViewModel
import com.clearn.app.ui.screens.playground.PlaygroundScreen
import com.clearn.app.ui.screens.playground.PlaygroundViewModel
import com.clearn.app.ui.screens.progress.ProgressScreen
import com.clearn.app.ui.screens.progress.ProgressViewModel
import com.clearn.app.ui.screens.pyq.PyqScreen
import com.clearn.app.ui.screens.pyq.PyqViewModel
import com.clearn.app.ui.screens.quizzes.QuizRunnerScreen
import com.clearn.app.ui.screens.quizzes.QuizRunnerViewModel
import com.clearn.app.ui.screens.quizzes.QuizzesScreen
import com.clearn.app.ui.screens.quizzes.QuizzesViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current.applicationContext as CLearnApplication
    val userProgressRepo = context.userProgressRepository
    val syllabusRepo = SyllabusRepository()
    val quizRepo = QuizRepository()
    val pyqRepo = PyqRepository()
    val playgroundRepo = PlaygroundRepository(RetrofitClient.judge0ApiService)

    NavHost(
        navController = navController,
        startDestination = Screen.Learn.route,
        modifier = modifier
    ) {
        composable(Screen.Learn.route) {
            val learnViewModel: LearnViewModel = viewModel(
                factory = LearnViewModel.Factory(syllabusRepo, userProgressRepo)
            )
            LearnScreen(
                viewModel = learnViewModel,
                onTopicClick = { unitId, topicId ->
                    navController.navigate(Screen.LessonDetail.createRoute(unitId, topicId))
                }
            )
        }

        composable(
            route = Screen.LessonDetail.route,
            arguments = listOf(
                navArgument("unitId") { type = NavType.IntType },
                navArgument("topicId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val unitId = backStackEntry.arguments?.getInt("unitId") ?: 1
            val topicId = backStackEntry.arguments?.getString("topicId") ?: "u1_t0"

            val lessonDetailViewModel: LessonDetailViewModel = viewModel(
                factory = LessonDetailViewModel.Factory(unitId, topicId, syllabusRepo, userProgressRepo)
            )

            LessonDetailScreen(
                viewModel = lessonDetailViewModel,
                onBackClick = { navController.popBackStack() },
                onNavigateToPlayground = { code ->
                    navController.navigate(Screen.Playground.route)
                },
                onNavigateToTopic = { newUnitId, newTopicId ->
                    navController.navigate(Screen.LessonDetail.createRoute(newUnitId, newTopicId)) {
                        popUpTo(Screen.Learn.route)
                    }
                }
            )
        }

        composable(Screen.Playground.route) {
            val playgroundViewModel: PlaygroundViewModel = viewModel(
                factory = PlaygroundViewModel.Factory(playgroundRepo)
            )
            PlaygroundScreen(
                viewModel = playgroundViewModel
            )
        }

        composable(Screen.Quizzes.route) {
            val quizzesViewModel: QuizzesViewModel = viewModel(
                factory = QuizzesViewModel.Factory(quizRepo, userProgressRepo)
            )
            QuizzesScreen(
                viewModel = quizzesViewModel,
                onStartQuiz = { unitId ->
                    navController.navigate(Screen.QuizRunner.createRoute(unitId))
                }
            )
        }

        composable(
            route = Screen.QuizRunner.route,
            arguments = listOf(
                navArgument("unitId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val unitId = backStackEntry.arguments?.getInt("unitId") ?: 1
            val quizRunnerViewModel: QuizRunnerViewModel = viewModel(
                factory = QuizRunnerViewModel.Factory(unitId, quizRepo, userProgressRepo)
            )
            QuizRunnerScreen(
                viewModel = quizRunnerViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.Pyq.route) {
            val pyqViewModel: PyqViewModel = viewModel(
                factory = PyqViewModel.Factory(pyqRepo)
            )
            PyqScreen(
                viewModel = pyqViewModel
            )
        }

        composable(Screen.Progress.route) {
            val progressViewModel: ProgressViewModel = viewModel(
                factory = ProgressViewModel.Factory(syllabusRepo, userProgressRepo)
            )
            ProgressScreen(
                viewModel = progressViewModel,
                onBookmarkClick = { unitId, topicId ->
                    navController.navigate(Screen.LessonDetail.createRoute(unitId, topicId))
                }
            )
        }
    }
}
