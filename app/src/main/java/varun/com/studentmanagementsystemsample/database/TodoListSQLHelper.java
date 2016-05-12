package varun.com.studentmanagementsystemsample.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by VarunBarve on 21/04/2016.
 */
public class TodoListSQLHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "com.javapapers.androidtodo";
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String TODO_TITLE = "todo";
    public static final String TODO_DESC = "des";

    public static final String _ID = BaseColumns._ID;

    Context context;
    SQLiteDatabase db;

    public TodoListSQLHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String createTodoListTable = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TODO_TITLE + " TEXT, " + TODO_DESC + "TEXT)";
        sqlDB.execSQL(createTodoListTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqlDB);
    }

    public Cursor getTodoList() {
        String query = "select * from " + TABLE_NAME;
        Cursor C = db.rawQuery(query, null);
        return C;
    }
}
