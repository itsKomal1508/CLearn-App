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
import com.clearn.app.data.local.entity.QuizResultEntity;
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
public final class QuizResultDao_Impl implements QuizResultDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QuizResultEntity> __insertionAdapterOfQuizResultEntity;

  public QuizResultDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuizResultEntity = new EntityInsertionAdapter<QuizResultEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `quiz_results` (`unitId`,`score`,`totalQuestions`,`passed`,`attemptedAt`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QuizResultEntity entity) {
        statement.bindLong(1, entity.getUnitId());
        statement.bindLong(2, entity.getScore());
        statement.bindLong(3, entity.getTotalQuestions());
        final int _tmp = entity.getPassed() ? 1 : 0;
        statement.bindLong(4, _tmp);
        statement.bindLong(5, entity.getAttemptedAt());
      }
    };
  }

  @Override
  public Object saveQuizResult(final QuizResultEntity result,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfQuizResultEntity.insert(result);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<QuizResultEntity> getQuizResultForUnit(final int unitId) {
    final String _sql = "SELECT * FROM quiz_results WHERE unitId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, unitId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"quiz_results"}, new Callable<QuizResultEntity>() {
      @Override
      @Nullable
      public QuizResultEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUnitId = CursorUtil.getColumnIndexOrThrow(_cursor, "unitId");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfTotalQuestions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalQuestions");
          final int _cursorIndexOfPassed = CursorUtil.getColumnIndexOrThrow(_cursor, "passed");
          final int _cursorIndexOfAttemptedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "attemptedAt");
          final QuizResultEntity _result;
          if (_cursor.moveToFirst()) {
            final int _tmpUnitId;
            _tmpUnitId = _cursor.getInt(_cursorIndexOfUnitId);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final int _tmpTotalQuestions;
            _tmpTotalQuestions = _cursor.getInt(_cursorIndexOfTotalQuestions);
            final boolean _tmpPassed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPassed);
            _tmpPassed = _tmp != 0;
            final long _tmpAttemptedAt;
            _tmpAttemptedAt = _cursor.getLong(_cursorIndexOfAttemptedAt);
            _result = new QuizResultEntity(_tmpUnitId,_tmpScore,_tmpTotalQuestions,_tmpPassed,_tmpAttemptedAt);
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

  @Override
  public Flow<List<QuizResultEntity>> getAllQuizResults() {
    final String _sql = "SELECT * FROM quiz_results";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"quiz_results"}, new Callable<List<QuizResultEntity>>() {
      @Override
      @NonNull
      public List<QuizResultEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUnitId = CursorUtil.getColumnIndexOrThrow(_cursor, "unitId");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfTotalQuestions = CursorUtil.getColumnIndexOrThrow(_cursor, "totalQuestions");
          final int _cursorIndexOfPassed = CursorUtil.getColumnIndexOrThrow(_cursor, "passed");
          final int _cursorIndexOfAttemptedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "attemptedAt");
          final List<QuizResultEntity> _result = new ArrayList<QuizResultEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QuizResultEntity _item;
            final int _tmpUnitId;
            _tmpUnitId = _cursor.getInt(_cursorIndexOfUnitId);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final int _tmpTotalQuestions;
            _tmpTotalQuestions = _cursor.getInt(_cursorIndexOfTotalQuestions);
            final boolean _tmpPassed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPassed);
            _tmpPassed = _tmp != 0;
            final long _tmpAttemptedAt;
            _tmpAttemptedAt = _cursor.getLong(_cursorIndexOfAttemptedAt);
            _item = new QuizResultEntity(_tmpUnitId,_tmpScore,_tmpTotalQuestions,_tmpPassed,_tmpAttemptedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
