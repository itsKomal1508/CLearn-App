package com.clearn.app.data.repository

import com.clearn.app.data.local.dao.BookmarkDao
import com.clearn.app.data.local.dao.LessonProgressDao
import com.clearn.app.data.local.dao.QuizResultDao
import com.clearn.app.data.local.dao.StreakDao
import com.clearn.app.data.local.entity.BookmarkEntity
import com.clearn.app.data.local.entity.LessonProgressEntity
import com.clearn.app.data.local.entity.QuizResultEntity
import com.clearn.app.data.local.entity.StreakEntity
import kotlinx.coroutines.flow.Flow

class UserProgressRepository(
    private val lessonProgressDao: LessonProgressDao,
    private val quizResultDao: QuizResultDao,
    private val streakDao: StreakDao,
    private val bookmarkDao: BookmarkDao
) {
    // Lesson Progress Streams & Actions
    fun getProgressForUnit(unitId: Int): Flow<List<LessonProgressEntity>> =
        lessonProgressDao.getProgressForUnit(unitId)

    fun getAllProgress(): Flow<List<LessonProgressEntity>> =
        lessonProgressDao.getAllProgress()

    fun getProgressForTopic(topicId: String): Flow<LessonProgressEntity?> =
        lessonProgressDao.getProgressForTopic(topicId)

    suspend fun markTopicCompleted(topicId: String, unitId: Int) {
        val progress = LessonProgressEntity(
            topicId = topicId,
            unitId = unitId,
            isCompleted = true
        )
        lessonProgressDao.insertOrUpdateProgress(progress)
    }

    // Quiz Streams & Actions
    fun getQuizResultForUnit(unitId: Int): Flow<QuizResultEntity?> =
        quizResultDao.getQuizResultForUnit(unitId)

    fun getAllQuizResults(): Flow<List<QuizResultEntity>> =
        quizResultDao.getAllQuizResults()

    suspend fun saveQuizResult(unitId: Int, score: Int, totalQuestions: Int, passed: Boolean) {
        val result = QuizResultEntity(
            unitId = unitId,
            score = score,
            totalQuestions = totalQuestions,
            passed = passed
        )
        quizResultDao.saveQuizResult(result)
    }

    // Streak Streams & Actions
    fun getStreak(): Flow<StreakEntity?> = streakDao.getStreak()

    suspend fun updateStreak(currentStreak: Int, lastOpenDate: String, bestStreak: Int) {
        val streak = StreakEntity(
            id = 1,
            currentStreak = currentStreak,
            lastOpenDate = lastOpenDate,
            bestStreak = maxOf(currentStreak, bestStreak)
        )
        streakDao.updateStreak(streak)
    }

    // Bookmark Streams & Actions
    fun getAllBookmarks(): Flow<List<BookmarkEntity>> = bookmarkDao.getAllBookmarks()

    fun isBookmarked(topicId: String): Flow<Boolean> = bookmarkDao.isBookmarked(topicId)

    suspend fun toggleBookmark(topicId: String, unitId: Int, topicTitle: String, currentlyBookmarked: Boolean) {
        if (currentlyBookmarked) {
            bookmarkDao.removeBookmark(topicId)
        } else {
            val bookmark = BookmarkEntity(
                topicId = topicId,
                unitId = unitId,
                topicTitle = topicTitle
            )
            bookmarkDao.addBookmark(bookmark)
        }
    }
}
