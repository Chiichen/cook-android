package cn.chiichen.cook.model.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import cn.chiichen.cook.model.entity.Recipe;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RecipeDao_Impl implements RecipeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Recipe> __insertionAdapterOfRecipe;

  public RecipeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRecipe = new EntityInsertionAdapter<Recipe>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Recipe` (`name`,`stuff`,`bv`,`difficulty`,`tags`,`methods`,`tools`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Recipe entity) {
        statement.bindString(1, entity.getName());
        statement.bindString(2, entity.getStuff());
        statement.bindString(3, entity.getBv());
        statement.bindString(4, entity.getDifficulty());
        statement.bindString(5, entity.getTags());
        statement.bindString(6, entity.getMethods());
        statement.bindString(7, entity.getTools());
      }
    };
  }

  @Override
  public Object insertRecipe(final Recipe recipe, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRecipe.insert(recipe);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public List<Recipe> getAllRecipes() {
    final String _sql = "SELECT * From Recipe";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfStuff = CursorUtil.getColumnIndexOrThrow(_cursor, "stuff");
      final int _cursorIndexOfBv = CursorUtil.getColumnIndexOrThrow(_cursor, "bv");
      final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfMethods = CursorUtil.getColumnIndexOrThrow(_cursor, "methods");
      final int _cursorIndexOfTools = CursorUtil.getColumnIndexOrThrow(_cursor, "tools");
      final List<Recipe> _result = new ArrayList<Recipe>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Recipe _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpStuff;
        _tmpStuff = _cursor.getString(_cursorIndexOfStuff);
        final String _tmpBv;
        _tmpBv = _cursor.getString(_cursorIndexOfBv);
        final String _tmpDifficulty;
        _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
        final String _tmpTags;
        _tmpTags = _cursor.getString(_cursorIndexOfTags);
        final String _tmpMethods;
        _tmpMethods = _cursor.getString(_cursorIndexOfMethods);
        final String _tmpTools;
        _tmpTools = _cursor.getString(_cursorIndexOfTools);
        _item = new Recipe(_tmpName,_tmpStuff,_tmpBv,_tmpDifficulty,_tmpTags,_tmpMethods,_tmpTools);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Recipe> getRandomRecipe(final int num) {
    final String _sql = "SELECT * FROM Recipe ORDER BY RANDOM() LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, num);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfStuff = CursorUtil.getColumnIndexOrThrow(_cursor, "stuff");
      final int _cursorIndexOfBv = CursorUtil.getColumnIndexOrThrow(_cursor, "bv");
      final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
      final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
      final int _cursorIndexOfMethods = CursorUtil.getColumnIndexOrThrow(_cursor, "methods");
      final int _cursorIndexOfTools = CursorUtil.getColumnIndexOrThrow(_cursor, "tools");
      final List<Recipe> _result = new ArrayList<Recipe>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Recipe _item;
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpStuff;
        _tmpStuff = _cursor.getString(_cursorIndexOfStuff);
        final String _tmpBv;
        _tmpBv = _cursor.getString(_cursorIndexOfBv);
        final String _tmpDifficulty;
        _tmpDifficulty = _cursor.getString(_cursorIndexOfDifficulty);
        final String _tmpTags;
        _tmpTags = _cursor.getString(_cursorIndexOfTags);
        final String _tmpMethods;
        _tmpMethods = _cursor.getString(_cursorIndexOfMethods);
        final String _tmpTools;
        _tmpTools = _cursor.getString(_cursorIndexOfTools);
        _item = new Recipe(_tmpName,_tmpStuff,_tmpBv,_tmpDifficulty,_tmpTags,_tmpMethods,_tmpTools);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
