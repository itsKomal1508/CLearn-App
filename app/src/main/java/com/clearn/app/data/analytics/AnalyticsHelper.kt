package com.clearn.app.data.analytics
import android.content.Context
import android.util.Log

object AnalyticsHelper {
    private const val TAG = "CLearnAnalytics"

    fun init(context: Context) {
        Log.d(TAG, "Analytics initialized in local telemetry mode.")
    }

    fun logLessonCompleted(topicId: String, unitId: Int) {
        Log.d(TAG, "Event logged [lesson_completed]: topic_id=$topicId, unit_id=$unitId")
    }

    fun logQuizSubmitted(unitId: Int, score: Int, totalQuestions: Int, passed: Boolean) {
        Log.d(TAG, "Event logged [quiz_submitted]: unit_id=$unitId, score=$score/$totalQuestions, passed=$passed")
    }

    fun logCodeRunAttempted(success: Boolean) {
        Log.d(TAG, "Event logged [code_run_attempted]: success=$success")
    }

    fun logStreakUpdated(streakCount: Int) {
        Log.d(TAG, "Event logged [streak_updated]: streak_count=$streakCount")
    }
}
