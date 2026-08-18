package com.clearn.app.ui.screens.lessondetail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clearn.app.ui.components.visualizers.ArrayVisualizerCard
import com.clearn.app.ui.components.visualizers.CompilationVisualizerCard
import com.clearn.app.ui.components.visualizers.PointerVisualizerCard
import com.clearn.app.ui.components.visualizers.StructVsUnionVisualizerCard
import com.clearn.app.ui.theme.CodeBackground
import com.clearn.app.ui.theme.CodeMonospaceStyle
import com.clearn.app.ui.theme.FreshGreen
import com.clearn.app.ui.theme.HotCoralPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    viewModel: LessonDetailViewModel,
    onBackClick: () -> Unit,
    onNavigateToPlayground: (code: String?) -> Unit,
    onNavigateToTopic: (unitId: Int, topicId: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val lesson = uiState.microLesson
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.unitTitle,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleBookmark() }) {
                        Icon(
                            imageVector = if (uiState.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (uiState.isBookmarked) FreshGreen else MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        if (lesson != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Topic Title Header
                Text(
                    text = lesson.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                // Render Interactive Concept Visualizer for flagged topics!
                when (lesson.topicId) {
                    "u1_t3" -> CompilationVisualizerCard()
                    "u6_t1" -> ArrayVisualizerCard()
                    "u7_t1" -> PointerVisualizerCard()
                    "u8_t2" -> StructVsUnionVisualizerCard()
                }

                // Explanation Box with Dual Language Mode (English / Hinglish)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Concept Breakdown",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )

                            // Language Toggle Chips
                            if (lesson.hinglishExplanation != null) {
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    FilterChip(
                                        selected = !uiState.isHinglishMode,
                                        onClick = { if (uiState.isHinglishMode) viewModel.toggleLanguageMode() },
                                        label = { Text("🇬🇧 Eng", style = MaterialTheme.typography.labelSmall) }
                                    )
                                    FilterChip(
                                        selected = uiState.isHinglishMode,
                                        onClick = { if (!uiState.isHinglishMode) viewModel.toggleLanguageMode() },
                                        label = { Text("🇮🇳 Hinglish", style = MaterialTheme.typography.labelSmall) }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val explanationText = if (uiState.isHinglishMode && lesson.hinglishExplanation != null) {
                            lesson.hinglishExplanation
                        } else {
                            lesson.explanation
                        }

                        Text(
                            text = explanationText,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.2f,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                // C Code Example Block (If present)
                lesson.codeSnippet?.let { code ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = CodeBackground)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "C CODE EXAMPLE",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                    fontWeight = FontWeight.Bold
                                )

                                Row {
                                    IconButton(
                                        onClick = {
                                            clipboardManager.setText(AnnotatedString(code))
                                            Toast.makeText(context, "Code copied to clipboard!", Toast.LENGTH_SHORT).show()
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ContentCopy,
                                            contentDescription = "Copy",
                                            tint = MaterialTheme.colorScheme.onPrimary,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = code,
                                style = CodeMonospaceStyle,
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Requirement 3: Primary CTA Button in Hot Coral-Pink (#FF5D8F)
                            Button(
                                onClick = { onNavigateToPlayground(code) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = HotCoralPink
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Run",
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Try in C Playground")
                            }
                        }
                    }
                }

                // Key Takeaways List
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "💡 Key Takeaways",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        lesson.keyTakeaways.forEach { takeaway ->
                            Row(
                                modifier = Modifier.padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                // Requirement 1: Fresh Green (#3DDC84) checkmark icon
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = FreshGreen,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .padding(top = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = takeaway,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Requirement 3: Primary Action Button in Hot Coral-Pink (#FF5D8F) & Fresh Green (#3DDC84) when completed
                Button(
                    onClick = {
                        viewModel.markCompleted()
                        Toast.makeText(context, "Topic marked as complete! 🎉", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (uiState.isCompleted) FreshGreen else HotCoralPink
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Complete"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (uiState.isCompleted) "Completed! ✓" else "Mark Topic as Complete",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Next / Prev Topic Navigation Controls
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (uiState.prevTopicId != null) {
                        OutlinedButton(
                            onClick = { onNavigateToTopic(uiState.unitId, uiState.prevTopicId!!) }
                        ) {
                            Text("← Previous")
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    if (uiState.nextTopicId != null) {
                        // Requirement 3: Primary CTA Button in Hot Coral-Pink (#FF5D8F)
                        Button(
                            onClick = { onNavigateToTopic(uiState.unitId, uiState.nextTopicId!!) },
                            colors = ButtonDefaults.buttonColors(containerColor = HotCoralPink)
                        ) {
                            Text("Next Topic →")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
