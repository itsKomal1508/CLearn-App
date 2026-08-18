package com.clearn.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.clearn.app.data.local.manager.StreakManager
import com.clearn.app.ui.components.BottomNavigationBar
import com.clearn.app.ui.navigation.AppNavGraph
import com.clearn.app.ui.theme.CLearnTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val app = application as CLearnApplication
        lifecycleScope.launch {
            StreakManager.checkAndUpdateStreak(app.userProgressRepository)
        }

        setContent {
            CLearnTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    CLearnMainApp()
                }
            }
        }
    }
}

@Composable
fun CLearnMainApp() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
