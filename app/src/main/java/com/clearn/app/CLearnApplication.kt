package com.clearn.app

import android.app.Application
import com.clearn.app.data.analytics.AnalyticsHelper
import com.clearn.app.data.local.database.AppDatabase
import com.clearn.app.data.repository.UserProgressRepository

class CLearnApplication : Application() {

    val database by lazy { AppDatabase.getInstance(this) }

    val userProgressRepository by lazy {
        UserProgressRepository(
            database.lessonProgressDao(),
            database.quizResultDao(),
            database.streakDao(),
            database.bookmarkDao()
        )
    }

    override fun onCreate() {
        super.onCreate()
        AnalyticsHelper.init(this)
    }
}
