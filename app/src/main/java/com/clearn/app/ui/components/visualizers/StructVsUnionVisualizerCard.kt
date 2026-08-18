package com.clearn.app.ui.components.visualizers

import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clearn.app.ui.theme.CodeMonospaceStyle

@Composable
fun StructVsUnionVisualizerCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🎨 Struct vs Union Memory Layout Comparison",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Notice how struct allocates separate RAM bytes while union shares one memory block",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Struct Row
            Text("struct Student { int roll; float marks; } (Total Size: 8 Bytes)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .clip(RoundedCornerShape(topStart = 6.dp, bottomStart = 6.dp))
                        .background(Color(0xFF2563EB)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("roll (4 Bytes)", color = Color.White, style = CodeMonospaceStyle)
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(38.dp)
                        .clip(RoundedCornerShape(topEnd = 6.dp, bottomEnd = 6.dp))
                        .background(Color(0xFF10B981)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("marks (4 Bytes)", color = Color.White, style = CodeMonospaceStyle)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Union Row
            Text("union Data { int roll; float marks; } (Total Size: 4 Bytes - Shared!)", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFF97316)),
                contentAlignment = Alignment.Center
            ) {
                Text("Shared Memory Block (4 Bytes for roll OR marks)", color = Color.White, style = CodeMonospaceStyle)
            }
        }
    }
}
