package com.clearn.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey
    val topicId: String,
    val unitId: Int,
    val topicTitle: String,
    val bookmarkedAt: Long = System.currentTimeMillis()
)
