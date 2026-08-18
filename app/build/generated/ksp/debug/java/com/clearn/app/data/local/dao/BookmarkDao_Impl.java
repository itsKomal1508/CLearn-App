package com.clearn.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.clearn.app.data.local.entity.BookmarkEntity;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BookmarkDao_Impl implements BookmarkDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BookmarkEntity> __insertionAdapterOfBookmarkEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemoveBookmark;

  public BookmarkDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookmarkEntity = new EntityInsertionAdapter<BookmarkEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `bookmarks` (`topicId`,`unitId`,`topicTitle`,`bookmarkedAt`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookmarkEntity entity) {
        statement.bindString(1, entity.getTopicId());
        statement.bindLong(2, entity.getUnitId());
        statement.bindString(3, entity.getTopicTitle());
        statement.bindLong(4, entity.getBookmarkedAt());
      }
    };
    this.__preparedStmtOfRemoveBookmark = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM bookmarks WHERE topicId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object addBookmark(final BookmarkEntity bookmark,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBookmarkEntity.insert(bookmark);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeBookmark(final String topicId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveBookmark.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, topicId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveBookmark.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookmarkEntity>> getAllBookmarks() {
    final String _sql = "SELECT * FROM bookmarks ORDER BY bookmarkedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookmarks"}, new Callable<List<BookmarkEntity>>() {
      @Override
      @NonNull
      public List<BookmarkEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfUnitId = CursorUtil.getColumnIndexOrThrow(_cursor, "unitId");
          final int _cursorIndexOfTopicTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "topicTitle");
          final int _cursorIndexOfBookmarkedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "bookmarkedAt");
          final List<BookmarkEntity> _result = new ArrayList<BookmarkEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookmarkEntity _item;
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final int _tmpUnitId;
            _tmpUnitId = _cursor.getInt(_cursorIndexOfUnitId);
            final String _tmpTopicTitle;
            _tmpTopicTitle = _cursor.getString(_cursorIndexOfTopicTitle);
            final long _tmpBookmarkedAt;
            _tmpBookmarkedAt = _cursor.getLong(_cursorIndexOfBookmarkedAt);
            _item = new BookmarkEntity(_tmpTopicId,_tmpUnitId,_tmpTopicTitle,_tmpBookmarkedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Boolean> isBookmarked(final String topicId) {
    final String _sql = "SELECT EXISTS(SELECT 1 FROM bookmarks WHERE topicId = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, topicId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookmarks"}, new Callable<Boolean>() {
      @Override
      @NonNull
      public Boolean call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Boolean _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp != 0;
          } else {
            _result = false;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
