package com.clearn.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clearn.app.data.local.entity.LessonProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonProgressDao {
    @Query("SELECT * FROM lesson_progress WHERE unitId = :unitId")
    fun getProgressForUnit(unitId: Int): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress")
    fun getAllProgress(): Flow<List<LessonProgressEntity>>

    @Query("SELECT * FROM lesson_progress WHERE topicId = :topicId LIMIT 1")
    fun getProgressForTopic(topicId: String): Flow<LessonProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProgress(progress: LessonProgressEntity)
}
