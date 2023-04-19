package com.example.todo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqliteManager extends SQLiteOpenHelper {

    public static final String tag = "SqliteManager";

    public static final String databaseName = "todo-database.db";
    public static final int databaseVersion = 1;

    public SqliteManager(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE todo_items (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    title TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    priority INTEGER NOT NULL,\n" +
                "    due_date TEXT NOT NULL,\n" +
                "    notes TEXT\n" +
                ");\n";

        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS todo_items;\n";

        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }

    /* Method to insert a to-do item into the Sqlite database */
    public void insert(String title, String description, int priority, String due_date, String notes) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("priority", priority);
        contentValues.put("due_date", due_date);
        contentValues.put("notes", notes);

        sqLiteDatabase.insert("todo_items", null, contentValues);
        Log.i(tag, " inserted item into database");
    }

    /* Method to update an existing to-do item within the Sqlite database by searching it on the id*/
    public void update(String id, String title, String description, int priority, String due_date, String notes) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        contentValues.put("priority", priority);
        contentValues.put("due_date", due_date);
        contentValues.put("notes", notes);

        sqLiteDatabase.update("todo_items", contentValues, "id = ?", new String[]{id});
        Log.i(tag, " updated item from database");
    }

    /*Method to delete an existing to-do item from the sqlite database by searching it on the id*/
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.delete("todo_items", "id = ?", new String[]{id});
        Log.i(tag, " delete item from database");
    }

    /*Method to get al existing to-do items from the sqlite database and returning them in a cursor*/
    public Cursor getAll() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String getAllQuery = "SELECT * FROM todo_items;\n";

        Cursor cursor = null;
        if (sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(getAllQuery, null);
        } else {
            Log.e(tag, " database is empty or not created so couldn't receive data");
        }

        Log.i(tag, " received all items from database");
        return cursor;
    }
}