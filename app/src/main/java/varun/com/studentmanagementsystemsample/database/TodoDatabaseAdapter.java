package varun.com.studentmanagementsystemsample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by VarunBarve on 21/04/2016.
 */
public class TodoDatabaseAdapter {

    public static final String DB_NAME = "MelstarSQLite";
    public static final String TABLE_NAME = "TODO_LIST";
    public static final String TODO_TITLE = "todo";
    public static final String TODO_DESC = "des";
    public static final String _ID = BaseColumns._ID;
    private static String CREATE_TABLE;
    Context context;
    private DatabaseHelper DBHhelper;
    private SQLiteDatabase db;

    public TodoDatabaseAdapter(Context ctx) {
        this.context = ctx;
        DBHhelper = new DatabaseHelper(ctx);

        CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TODO_TITLE + " TEXT, " + TODO_DESC + "TEXT)";
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(CREATE_TABLE);
            System.out.println("Table is created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS tiles");
            onCreate(db);
        }
    }

}
