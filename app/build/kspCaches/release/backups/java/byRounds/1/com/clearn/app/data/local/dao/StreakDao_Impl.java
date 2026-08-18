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
import com.clearn.app.data.local.entity.StreakEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StreakDao_Impl implements StreakDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StreakEntity> __insertionAdapterOfStreakEntity;

  public StreakDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStreakEntity = new EntityInsertionAdapter<StreakEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `streaks` (`id`,`currentStreak`,`lastOpenDate`,`bestStreak`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StreakEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getCurrentStreak());
        statement.bindString(3, entity.getLastOpenDate());
        statement.bindLong(4, entity.getBestStreak());
      }
    };
  }

  @Override
  public Object updateStreak(final StreakEntity streak,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStreakEntity.insert(streak);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<StreakEntity> getStreak() {
    final String _sql = "SELECT * FROM streaks WHERE id = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"streaks"}, new Callable<StreakEntity>() {
      @Override
      @Nullable
      public StreakEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCurrentStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "currentStreak");
          final int _cursorIndexOfLastOpenDate = CursorUtil.getColumnIndexOrThrow(_cursor, "lastOpenDate");
          final int _cursorIndexOfBestStreak = CursorUtil.getColumnIndexOrThrow(_cursor, "bestStreak");
          final StreakEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpCurrentStreak;
            _tmpCurrentStreak = _cursor.getInt(_cursorIndexOfCurrentStreak);
            final String _tmpLastOpenDate;
            _tmpLastOpenDate = _cursor.getString(_cursorIndexOfLastOpenDate);
            final int _tmpBestStreak;
            _tmpBestStreak = _cursor.getInt(_cursorIndexOfBestStreak);
            _result = new StreakEntity(_tmpId,_tmpCurrentStreak,_tmpLastOpenDate,_tmpBestStreak);
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
