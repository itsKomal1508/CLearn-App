package com.clearn.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.clearn.app.data.local.entity.LessonProgressEntity;
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
public final class LessonProgressDao_Impl implements LessonProgressDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LessonProgressEntity> __insertionAdapterOfLessonProgressEntity;

  public LessonProgressDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLessonProgressEntity = new EntityInsertionAdapter<LessonProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `lesson_progress` (`topicId`,`unitId`,`isCompleted`,`completedAt`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LessonProgressEntity entity) {
        statement.bindString(1, entity.getTopicId());
        statement.bindLong(2, entity.getUnitId());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(3, _tmp);
        statement.bindLong(4, entity.getCompletedAt());
      }
    };
  }

  @Override
  public Object insertOrUpdateProgress(final LessonProgressEntity progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLessonProgressEntity.insert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LessonProgressEntity>> getProgressForUnit(final int unitId) {
    final String _sql = "SELECT * FROM lesson_progress WHERE unitId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, unitId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"lesson_progress"}, new Callable<List<LessonProgressEntity>>() {
      @Override
      @NonNull
      public List<LessonProgressEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfUnitId = CursorUtil.getColumnIndexOrThrow(_cursor, "unitId");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<LessonProgressEntity> _result = new ArrayList<LessonProgressEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LessonProgressEntity _item;
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final int _tmpUnitId;
            _tmpUnitId = _cursor.getInt(_cursorIndexOfUnitId);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            _item = new LessonProgressEntity(_tmpTopicId,_tmpUnitId,_tmpIsCompleted,_tmpCompletedAt);
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
  public Flow<List<LessonProgressEntity>> getAllProgress() {
    final String _sql = "SELECT * FROM lesson_progress";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"lesson_progress"}, new Callable<List<LessonProgressEntity>>() {
      @Override
      @NonNull
      public List<LessonProgressEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfUnitId = CursorUtil.getColumnIndexOrThrow(_cursor, "unitId");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<LessonProgressEntity> _result = new ArrayList<LessonProgressEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LessonProgressEntity _item;
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final int _tmpUnitId;
            _tmpUnitId = _cursor.getInt(_cursorIndexOfUnitId);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            _item = new LessonProgressEntity(_tmpTopicId,_tmpUnitId,_tmpIsCompleted,_tmpCompletedAt);
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
  public Flow<LessonProgressEntity> getProgressForTopic(final String topicId) {
    final String _sql = "SELECT * FROM lesson_progress WHERE topicId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, topicId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"lesson_progress"}, new Callable<LessonProgressEntity>() {
      @Override
      @Nullable
      public LessonProgressEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTopicId = CursorUtil.getColumnIndexOrThrow(_cursor, "topicId");
          final int _cursorIndexOfUnitId = CursorUtil.getColumnIndexOrThrow(_cursor, "unitId");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final LessonProgressEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpTopicId;
            _tmpTopicId = _cursor.getString(_cursorIndexOfTopicId);
            final int _tmpUnitId;
            _tmpUnitId = _cursor.getInt(_cursorIndexOfUnitId);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final long _tmpCompletedAt;
            _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            _result = new LessonProgressEntity(_tmpTopicId,_tmpUnitId,_tmpIsCompleted,_tmpCompletedAt);
          } else {
            _result = null;
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
