package com.clearn.app.ui.screens.playground

import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clearn.app.ui.theme.CodeBackground
import com.clearn.app.ui.theme.CodeMonospaceStyle
import com.clearn.app.ui.theme.HotCoralPink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaygroundScreen(
    viewModel: PlaygroundViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    val cTemplates = listOf(
        "Hello World" to """
            #include <stdio.h>

            int main() {
                printf("Hello, MSBTE Diploma Students!\n");
                return 0;
            }
        """.trimIndent(),

        "scanf Input" to """
            #include <stdio.h>

            int main() {
                int age;
                printf("Enter your age: ");
                scanf("%d", &age);
                printf("You are %d years old!\n", age);
                return 0;
            }
        """.trimIndent(),

        "if-else Check" to """
            #include <stdio.h>

            int main() {
                int marks = 75;
                if (marks >= 40) {
                    printf("Result: Passed with Distinction!\n");
                } else {
                    printf("Result: Failed\n");
                }
                return 0;
            }
        """.trimIndent(),

        "for Loop" to """
            #include <stdio.h>

            int main() {
                for (int i = 1; i <= 5; i++) {
                    printf("Iteration %d: Learning C is fun!\n", i);
                }
                return 0;
            }
        """.trimIndent(),

        "Pointers" to """
            #include <stdio.h>

            int main() {
                int num = 42;
                int *ptr = &num;
                printf("Value of num: %d\n", num);
                printf("Memory address (&num): %p\n", (void*)ptr);
                printf("Dereferenced value (*ptr): %d\n", *ptr);
                return 0;
            }
        """.trimIndent()
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Interactive C Playground",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "GCC 9.2.0 Remote Execution Sandbox",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Template Quick Selector Row
            Text(
                text = "Load Starter C Template:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                cTemplates.forEach { (name, code) ->
                    FilterChip(
                        selected = false,
                        onClick = { viewModel.loadTemplate(code) },
                        label = { Text(name, style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }

            // C Source Code Editor Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CodeBackground)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Code,
                                contentDescription = "Editor",
                                tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "main.c",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = uiState.code,
                        onValueChange = { viewModel.updateCode(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp),
                        textStyle = CodeMonospaceStyle.copy(color = MaterialTheme.colorScheme.onPrimary),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }

            // Optional Standard Input (STDIN) Textbox
            OutlinedTextField(
                value = uiState.input,
                onValueChange = { viewModel.updateInput(it) },
                label = { Text("Standard Input (STDIN) - Optional for scanf") },
                placeholder = { Text("e.g. Enter inputs separated by newlines...") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 3,
                shape = RoundedCornerShape(12.dp)
            )

            // Requirement 3: Primary Action CTA Button in Hot Coral-Pink (#FF5D8F)
            Button(
                onClick = { viewModel.runCode() },
                enabled = !uiState.isRunning,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = HotCoralPink
                )
            ) {
                if (uiState.isRunning) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Compiling C Code...", style = MaterialTheme.typography.titleMedium)
                } else {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Run Code"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Run C Code ▶", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                }
            }

            // Execution Output Console Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (uiState.isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Terminal,
                                contentDescription = "Console",
                                tint = if (uiState.isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "OUTPUT CONSOLE",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (uiState.isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                            )
                        }

                        if (uiState.outputText != null) {
                            IconButton(
                                onClick = { viewModel.clearConsole() },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Clear Console",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    val displayOutput = when {
                        uiState.isRunning -> "Executing program on GCC cloud server..."
                        uiState.outputText != null -> uiState.outputText
                        else -> "Tap 'Run C Code ▶' above to view stdout output or syntax error breakdown."
                    }

                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        color = CodeBackground
                    ) {
                        Text(
                            text = displayOutput ?: "",
                            style = CodeMonospaceStyle,
                            color = if (uiState.isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    if (uiState.executionTime != null || uiState.memoryUsage != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "⏱ Execution Time: ${uiState.executionTime ?: "N/A"}s | Memory: ${uiState.memoryUsage ?: "N/A"} KB",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
