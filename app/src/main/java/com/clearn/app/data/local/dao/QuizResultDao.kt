package com.clearn.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clearn.app.data.local.entity.QuizResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quiz_results WHERE unitId = :unitId LIMIT 1")
    fun getQuizResultForUnit(unitId: Int): Flow<QuizResultEntity?>

    @Query("SELECT * FROM quiz_results")
    fun getAllQuizResults(): Flow<List<QuizResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuizResult(result: QuizResultEntity)
}
