package com.clearn.app.ui.screens.quizzes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clearn.app.ui.theme.CodeBackground
import com.clearn.app.ui.theme.CodeMonospaceStyle
import com.clearn.app.ui.theme.FreshGreen
import com.clearn.app.ui.theme.HotCoralPink
import com.clearn.app.ui.theme.SoftRedOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizRunnerScreen(
    viewModel: QuizRunnerViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Unit ${uiState.unitId} Quiz",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        if (uiState.isQuizFinished) {
            // Quiz Results Completion Screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = "Trophy",
                    tint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.size(72.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Quiz Completed! 🎉",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "You scored ${uiState.score} out of ${uiState.questions.size}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Requirement 3: Primary Action Button in Hot Coral-Pink (#FF5D8F)
                Button(
                    onClick = onBackClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = HotCoralPink)
                ) {
                    Text("Back to Quizzes List", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }
        } else if (uiState.questions.isNotEmpty()) {
            val currentQ = uiState.questions[uiState.currentQuestionIndex]
            val progressPct = (uiState.currentQuestionIndex + 1).toFloat() / uiState.questions.size

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Question Counter & Progress Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Question ${uiState.currentQuestionIndex + 1} of ${uiState.questions.size}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Score: ${uiState.score}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = FreshGreen
                    )
                }

                LinearProgressIndicator(
                    progress = { progressPct },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )

                // Question Statement Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = currentQ.questionText,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        // Code Snippet Box (If present for output prediction)
                        currentQ.codeSnippet?.let { snippet ->
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                color = CodeBackground
                            ) {
                                Text(
                                    text = snippet,
                                    style = CodeMonospaceStyle,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }
                    }
                }

                // Options List (Instant Green/Red Feedback)
                currentQ.options.forEachIndexed { index, optionText ->
                    val isSelected = uiState.selectedOptionIndex == index
                    val isCorrectOption = index == currentQ.correctOptionIndex

                    val cardColor = when {
                        !uiState.isAnswerSubmitted -> MaterialTheme.colorScheme.surface
                        isCorrectOption -> FreshGreen.copy(alpha = 0.15f)
                        isSelected && !isCorrectOption -> SoftRedOrange.copy(alpha = 0.15f)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    val borderColor = when {
                        !uiState.isAnswerSubmitted && isSelected -> MaterialTheme.colorScheme.primary
                        !uiState.isAnswerSubmitted -> MaterialTheme.colorScheme.outlineVariant
                        isCorrectOption -> FreshGreen
                        isSelected && !isCorrectOption -> SoftRedOrange
                        else -> MaterialTheme.colorScheme.outlineVariant
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !uiState.isAnswerSubmitted) {
                                viewModel.selectOption(index)
                            },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColor),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${('A' + index)}. ",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )

                            if (uiState.isAnswerSubmitted) {
                                if (isCorrectOption) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "Correct",
                                        tint = FreshGreen
                                    )
                                } else if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Incorrect",
                                        tint = SoftRedOrange
                                    )
                                }
                            }
                        }
                    }
                }

                // Instant Explanation Card after answer submission
                if (uiState.isAnswerSubmitted) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "💡 Explanation:",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentQ.explanation,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }

                    // Requirement 3: Primary Action Button in Hot Coral-Pink (#FF5D8F)
                    Button(
                        onClick = { viewModel.nextQuestion() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = HotCoralPink)
                    ) {
                        Text(
                            text = if (uiState.currentQuestionIndex < uiState.questions.size - 1) "Next Question →" else "Submit Quiz & View Results",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
