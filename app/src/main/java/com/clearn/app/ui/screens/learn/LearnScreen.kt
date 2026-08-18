package com.clearn.app.ui.screens.learn

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clearn.app.data.model.DiplomaUnit
import com.clearn.app.data.model.Topic
import com.clearn.app.ui.theme.FreshGreen
import com.clearn.app.ui.theme.SunnyYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(
    viewModel: LearnViewModel,
    onTopicClick: (unitId: Int, topicId: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val expandedUnits = remember { mutableStateMapOf<Int, Boolean>(1 to true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (uiState.isSearching) {
                        OutlinedTextField(
                            value = uiState.searchQuery,
                            onValueChange = { viewModel.updateSearchQuery(it) },
                            placeholder = { Text("Search topics, keywords, C code...") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            trailingIcon = {
                                IconButton(onClick = { viewModel.toggleSearchMode() }) {
                                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close Search")
                                }
                            }
                        )
                    } else {
                        Column {
                            Text(
                                text = "MSBTE C Programming",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Subject Code 312303 • K-Scheme",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    if (!uiState.isSearching) {
                        IconButton(onClick = { viewModel.toggleSearchMode() }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Global Search")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Show Search Results if user is searching
            if (uiState.isSearching && uiState.searchQuery.isNotEmpty()) {
                Text(
                    text = "Search Results (${uiState.searchResults.size})",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                if (uiState.searchResults.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No topics found matching '${uiState.searchQuery}'", style = MaterialTheme.typography.bodyMedium)
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(uiState.searchResults) { topic ->
                            SearchResultTopicCard(
                                topic = topic,
                                isCompleted = uiState.completedTopicIds.contains(topic.id),
                                onClick = { onTopicClick(topic.unitId, topic.id) }
                            )
                        }
                    }
                }
            } else {
                // Course Progress Overview Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = "Course Progress",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Overall Course Progress",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            // Requirement 2: Percentage text in Sunny Yellow (#FFC93C)
                            Text(
                                text = "${(uiState.overallProgressPercent * 100).toInt()}%",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = SunnyYellow
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Requirement 2: Progress bar fill in Sunny Yellow (#FFC93C)
                        LinearProgressIndicator(
                            progress = { uiState.overallProgressPercent },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = SunnyYellow,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "${uiState.completedTopicsCount} of ${uiState.totalTopicsCount} topics completed",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Syllabus Unit Accordion List
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(uiState.units) { unit ->
                        val isExpanded = expandedUnits[unit.id] == true
                        val unitCompletedCount = unit.topics.count { uiState.completedTopicIds.contains(it.id) }

                        UnitAccordionCard(
                            unit = unit,
                            isExpanded = isExpanded,
                            completedCount = unitCompletedCount,
                            totalCount = unit.topics.size,
                            completedTopicIds = uiState.completedTopicIds,
                            onToggleExpand = {
                                expandedUnits[unit.id] = !isExpanded
                            },
                            onTopicClick = { topicId ->
                                onTopicClick(unit.id, topicId)
                            }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultTopicCard(
    topic: Topic,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Requirement 1: Fresh Green (#3DDC84) checkmark icon for completed topics
            Icon(
                imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Book,
                contentDescription = null,
                tint = if (isCompleted) FreshGreen else MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Unit ${topic.unitId} • ${topic.estimatedMinutes} min read",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Open",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun UnitAccordionCard(
    unit: DiplomaUnit,
    isExpanded: Boolean,
    completedCount: Int,
    totalCount: Int,
    completedTopicIds: Set<String>,
    onToggleExpand: () -> Unit,
    onTopicClick: (topicId: String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            // Header Row (Unit Title & Expand Icon)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onToggleExpand)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${unit.unitNumber}: ${unit.title}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$completedCount/$totalCount completed • ${unit.topics.sumOf { it.estimatedMinutes }} mins total",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandMore else Icons.Default.ChevronRight,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Expandable Topic List
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    unit.topics.forEach { topic ->
                        val isTopicCompleted = completedTopicIds.contains(topic.id)
                        TopicItemRow(
                            topic = topic,
                            isCompleted = isTopicCompleted,
                            onClick = { onTopicClick(topic.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TopicItemRow(
    topic: Topic,
    isCompleted: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Requirement 1: Fresh Green (#3DDC84) checkmark icon for completed topics
            Icon(
                imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.Book,
                contentDescription = null,
                tint = if (isCompleted) FreshGreen else MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = topic.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${topic.estimatedMinutes} min read",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Open",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
