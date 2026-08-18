package com.clearn.app.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clearn.app.data.local.dao.BookmarkDao
import com.clearn.app.data.local.dao.LessonProgressDao
import com.clearn.app.data.local.dao.QuizResultDao
import com.clearn.app.data.local.dao.StreakDao
import com.clearn.app.data.local.entity.BookmarkEntity
import com.clearn.app.data.local.entity.LessonProgressEntity
import com.clearn.app.data.local.entity.QuizResultEntity
import com.clearn.app.data.local.entity.StreakEntity

@Database(
    entities = [
        LessonProgressEntity::class,
        QuizResultEntity::class,
        StreakEntity::class,
        BookmarkEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lessonProgressDao(): LessonProgressDao
    abstract fun quizResultDao(): QuizResultDao
    abstract fun streakDao(): StreakDao
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "clearn_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
