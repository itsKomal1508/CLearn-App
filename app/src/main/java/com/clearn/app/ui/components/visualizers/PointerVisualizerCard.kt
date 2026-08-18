package com.clearn.app.ui.components.visualizers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun PointerVisualizerCard() {
    var selectedVal by remember { mutableStateOf(42) }
    var inspectedAddress by remember { mutableStateOf("0x7ffe82a") }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🎨 Interactive Pointer Visualizer (RAM Memory Inspection)",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Tap on RAM cells to inspect memory address vs dereferenced value (*ptr)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Variable cell
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        selectedVal = 42
                        inspectedAddress = "0x7ffe82a"
                    }
                ) {
                    Text("num", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier
                            .size(70.dp, 50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF2563EB)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("42", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                    Text("&num: 0x7ffe82a", style = CodeMonospaceStyle.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
                }

                // Pointer cell
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        selectedVal = 42
                        inspectedAddress = "*ptr -> 42"
                    }
                ) {
                    Text("*ptr", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Box(
                        modifier = Modifier
                            .size(100.dp, 50.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF10B981)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("0x7ffe82a", color = Color.White, style = CodeMonospaceStyle)
                    }
                    Text("&ptr: 0x7ffe830", style = CodeMonospaceStyle.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Text(
                    text = "Inspected: num = 42 | *ptr dereferences address $inspectedAddress -> Value: $selectedVal",
                    style = CodeMonospaceStyle,
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}