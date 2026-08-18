package com.clearn.app.ui.components.visualizers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clearn.app.ui.theme.CodeMonospaceStyle

@Composable
fun CompilationVisualizerCard() {
    var activeStep by remember { mutableStateOf(0) }

    val steps = listOf(
        Pair("1. Source (.c)", "hello.c: Human readable C code with #include directives."),
        Pair("2. Preprocess", "Preprocessor expands #include <stdio.h> & macros into expanded source."),
        Pair("3. Compiler", "GCC Compiler converts expanded C into Assembly language (.s)."),
        Pair("4. Assembler", "Assembler converts Assembly instructions into Machine Code (.obj)."),
        Pair("5. Linker", "Linker combines object code with C standard library into hello.exe!")
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🎨 Step-by-Step C Compilation Pipeline",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Tap each stage to see what happens to your code under the hood",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                steps.forEachIndexed { idx, (label, _) ->
                    val isSelected = activeStep == idx
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 2.dp)
                            .height(36.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFF334155))
                            .clickable { activeStep = idx },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${idx + 1}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = steps[activeStep].first,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = steps[activeStep].second,
                        style = CodeMonospaceStyle,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
