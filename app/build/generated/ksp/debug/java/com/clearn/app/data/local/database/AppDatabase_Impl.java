package com.clearn.app.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.clearn.app.data.local.dao.BookmarkDao;
import com.clearn.app.data.local.dao.BookmarkDao_Impl;
import com.clearn.app.data.local.dao.LessonProgressDao;
import com.clearn.app.data.local.dao.LessonProgressDao_Impl;
import com.clearn.app.data.local.dao.QuizResultDao;
import com.clearn.app.data.local.dao.QuizResultDao_Impl;
import com.clearn.app.data.local.dao.StreakDao;
import com.clearn.app.data.local.dao.StreakDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile LessonProgressDao _lessonProgressDao;

  private volatile QuizResultDao _quizResultDao;

  private volatile StreakDao _streakDao;

  private volatile BookmarkDao _bookmarkDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `lesson_progress` (`topicId` TEXT NOT NULL, `unitId` INTEGER NOT NULL, `isCompleted` INTEGER NOT NULL, `completedAt` INTEGER NOT NULL, PRIMARY KEY(`topicId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `quiz_results` (`unitId` INTEGER NOT NULL, `score` INTEGER NOT NULL, `totalQuestions` INTEGER NOT NULL, `passed` INTEGER NOT NULL, `attemptedAt` INTEGER NOT NULL, PRIMARY KEY(`unitId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `streaks` (`id` INTEGER NOT NULL, `currentStreak` INTEGER NOT NULL, `lastOpenDate` TEXT NOT NULL, `bestStreak` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bookmarks` (`topicId` TEXT NOT NULL, `unitId` INTEGER NOT NULL, `topicTitle` TEXT NOT NULL, `bookmarkedAt` INTEGER NOT NULL, PRIMARY KEY(`topicId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f1ebf206882a0e1c68eb93ae5c2d6ba1')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `lesson_progress`");
        db.execSQL("DROP TABLE IF EXISTS `quiz_results`");
        db.execSQL("DROP TABLE IF EXISTS `streaks`");
        db.execSQL("DROP TABLE IF EXISTS `bookmarks`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsLessonProgress = new HashMap<String, TableInfo.Column>(4);
        _columnsLessonProgress.put("topicId", new TableInfo.Column("topicId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonProgress.put("unitId", new TableInfo.Column("unitId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonProgress.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLessonProgress.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLessonProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLessonProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLessonProgress = new TableInfo("lesson_progress", _columnsLessonProgress, _foreignKeysLessonProgress, _indicesLessonProgress);
        final TableInfo _existingLessonProgress = TableInfo.read(db, "lesson_progress");
        if (!_infoLessonProgress.equals(_existingLessonProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "lesson_progress(com.clearn.app.data.local.entity.LessonProgressEntity).\n"
                  + " Expected:\n" + _infoLessonProgress + "\n"
                  + " Found:\n" + _existingLessonProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsQuizResults = new HashMap<String, TableInfo.Column>(5);
        _columnsQuizResults.put("unitId", new TableInfo.Column("unitId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizResults.put("score", new TableInfo.Column("score", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizResults.put("totalQuestions", new TableInfo.Column("totalQuestions", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizResults.put("passed", new TableInfo.Column("passed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsQuizResults.put("attemptedAt", new TableInfo.Column("attemptedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysQuizResults = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesQuizResults = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoQuizResults = new TableInfo("quiz_results", _columnsQuizResults, _foreignKeysQuizResults, _indicesQuizResults);
        final TableInfo _existingQuizResults = TableInfo.read(db, "quiz_results");
        if (!_infoQuizResults.equals(_existingQuizResults)) {
          return new RoomOpenHelper.ValidationResult(false, "quiz_results(com.clearn.app.data.local.entity.QuizResultEntity).\n"
                  + " Expected:\n" + _infoQuizResults + "\n"
                  + " Found:\n" + _existingQuizResults);
        }
        final HashMap<String, TableInfo.Column> _columnsStreaks = new HashMap<String, TableInfo.Column>(4);
        _columnsStreaks.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreaks.put("currentStreak", new TableInfo.Column("currentStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreaks.put("lastOpenDate", new TableInfo.Column("lastOpenDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStreaks.put("bestStreak", new TableInfo.Column("bestStreak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStreaks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStreaks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStreaks = new TableInfo("streaks", _columnsStreaks, _foreignKeysStreaks, _indicesStreaks);
        final TableInfo _existingStreaks = TableInfo.read(db, "streaks");
        if (!_infoStreaks.equals(_existingStreaks)) {
          return new RoomOpenHelper.ValidationResult(false, "streaks(com.clearn.app.data.local.entity.StreakEntity).\n"
                  + " Expected:\n" + _infoStreaks + "\n"
                  + " Found:\n" + _existingStreaks);
        }
        final HashMap<String, TableInfo.Column> _columnsBookmarks = new HashMap<String, TableInfo.Column>(4);
        _columnsBookmarks.put("topicId", new TableInfo.Column("topicId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarks.put("unitId", new TableInfo.Column("unitId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarks.put("topicTitle", new TableInfo.Column("topicTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarks.put("bookmarkedAt", new TableInfo.Column("bookmarkedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBookmarks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBookmarks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBookmarks = new TableInfo("bookmarks", _columnsBookmarks, _foreignKeysBookmarks, _indicesBookmarks);
        final TableInfo _existingBookmarks = TableInfo.read(db, "bookmarks");
        if (!_infoBookmarks.equals(_existingBookmarks)) {
          return new RoomOpenHelper.ValidationResult(false, "bookmarks(com.clearn.app.data.local.entity.BookmarkEntity).\n"
                  + " Expected:\n" + _infoBookmarks + "\n"
                  + " Found:\n" + _existingBookmarks);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f1ebf206882a0e1c68eb93ae5c2d6ba1", "ed4bbc956a336c591ce55a509d6bd234");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "lesson_progress","quiz_results","streaks","bookmarks");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `lesson_progress`");
      _db.execSQL("DELETE FROM `quiz_results`");
      _db.execSQL("DELETE FROM `streaks`");
      _db.execSQL("DELETE FROM `bookmarks`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LessonProgressDao.class, LessonProgressDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(QuizResultDao.class, QuizResultDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StreakDao.class, StreakDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BookmarkDao.class, BookmarkDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LessonProgressDao lessonProgressDao() {
    if (_lessonProgressDao != null) {
      return _lessonProgressDao;
    } else {
      synchronized(this) {
        if(_lessonProgressDao == null) {
          _lessonProgressDao = new LessonProgressDao_Impl(this);
        }
        return _lessonProgressDao;
      }
    }
  }

  @Override
  public QuizResultDao quizResultDao() {
    if (_quizResultDao != null) {
      return _quizResultDao;
    } else {
      synchronized(this) {
        if(_quizResultDao == null) {
          _quizResultDao = new QuizResultDao_Impl(this);
        }
        return _quizResultDao;
      }
    }
  }

  @Override
  public StreakDao streakDao() {
    if (_streakDao != null) {
      return _streakDao;
    } else {
      synchronized(this) {
        if(_streakDao == null) {
          _streakDao = new StreakDao_Impl(this);
        }
        return _streakDao;
      }
    }
  }

  @Override
  public BookmarkDao bookmarkDao() {
    if (_bookmarkDao != null) {
      return _bookmarkDao;
    } else {
      synchronized(this) {
        if(_bookmarkDao == null) {
          _bookmarkDao = new BookmarkDao_Impl(this);
        }
        return _bookmarkDao;
      }
    }
  }
}
