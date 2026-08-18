package com.clearn.app.data.local.manager

import com.clearn.app.data.analytics.AnalyticsHelper
import com.clearn.app.data.local.entity.StreakEntity
import com.clearn.app.data.repository.UserProgressRepository
import kotlinx.coroutines.flow.firstOrNull
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object StreakManager {

    private fun getTodayDateString(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(Date())
    }

    private fun getYesterdayDateString(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    suspend fun checkAndUpdateStreak(repository: UserProgressRepository) {
        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()

        val currentStreakEntity = repository.getStreak().firstOrNull()

        if (currentStreakEntity == null) {
            repository.updateStreak(
                currentStreak = 1,
                lastOpenDate = today,
                bestStreak = 1
            )
            AnalyticsHelper.logStreakUpdated(1)
        } else {
            val lastDate = currentStreakEntity.lastOpenDate
            when (lastDate) {
                today -> {
                    AnalyticsHelper.logStreakUpdated(currentStreakEntity.currentStreak)
                }
                yesterday -> {
                    val newStreak = currentStreakEntity.currentStreak + 1
                    val newBest = maxOf(newStreak, currentStreakEntity.bestStreak)
                    repository.updateStreak(
                        currentStreak = newStreak,
                        lastOpenDate = today,
                        bestStreak = newBest
                    )
                    AnalyticsHelper.logStreakUpdated(newStreak)
                }
                else -> {
                    repository.updateStreak(
                        currentStreak = 1,
                        lastOpenDate = today,
                        bestStreak = currentStreakEntity.bestStreak
                    )
                    AnalyticsHelper.logStreakUpdated(1)
                }
            }
        }
    }
}
