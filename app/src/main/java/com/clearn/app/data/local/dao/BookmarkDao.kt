package com.clearn.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clearn.app.data.local.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks ORDER BY bookmarkedAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE topicId = :topicId)")
    fun isBookmarked(topicId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE topicId = :topicId")
    suspend fun removeBookmark(topicId: String)
}
