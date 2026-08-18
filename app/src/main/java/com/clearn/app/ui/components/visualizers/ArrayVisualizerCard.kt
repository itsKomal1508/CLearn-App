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
import androidx.compose.foundation.layout.size
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
fun ArrayVisualizerCard() {
    var selectedIndex by remember { mutableStateOf(0) }
    val arrayData = listOf(85, 90, 78, 92)
    val addresses = listOf("1000", "1004", "1008", "1012")

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🎨 Interactive Array Memory Visualizer",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Tap any locker cell to inspect contiguous 4-byte int RAM memory allocation",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                arrayData.forEachIndexed { index, value ->
                    val isSelected = selectedIndex == index
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { selectedIndex = index }
                    ) {
                        Text("marks[$index]", style = MaterialTheme.typography.labelSmall)
                        Box(
                            modifier = Modifier
                                .size(55.dp, 45.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) MaterialTheme.colorScheme.primary else Color(0xFF334155)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("$value", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Text("0x${addresses[index]}", style = CodeMonospaceStyle.copy(color = MaterialTheme.colorScheme.onSecondaryContainer))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Text(
                    text = "Locker marks[$selectedIndex] = ${arrayData[selectedIndex]} | Sequential RAM Address: 0x${addresses[selectedIndex]} (+4 bytes)",
                    style = CodeMonospaceStyle,
                    modifier = Modifier.padding(10.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
