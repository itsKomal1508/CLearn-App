package com.clearn.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clearn.app.data.local.entity.StreakEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StreakDao {
    @Query("SELECT * FROM streaks WHERE id = 1 LIMIT 1")
    fun getStreak(): Flow<StreakEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStreak(streak: StreakEntity)
}
