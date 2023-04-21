package com.example.todo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.todo.model.TodoItem;

import java.util.ArrayList;

public class SqliteManager extends SQLiteOpenHelper {

    public static final String tag = "SqliteManager";

    public static final String databaseName = "todo-database.db";
    public static final int databaseVersion = 1;
    public static final String tableName = "todo";

    public SqliteManager(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    /* Method to create the database with the table*/
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE todo (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    title TEXT NOT NULL,\n" +
                "    description TEXT,\n" +
                "    due_date TEXT NOT NULL,\n" +
                "    notes TEXT\n" +
                ");";

        sqLiteDatabase.execSQL(createTableQuery);
    }

    /* Method to recreate the database when there where changes in the table*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableQuery = "DROP TABLE IF EXISTS todo;\n";

        sqLiteDatabase.execSQL(dropTableQuery);
        onCreate(sqLiteDatabase);
    }

    /* Method to insert a to-do item into the Sqlite database */
    public void insert(TodoItem todoItem) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", todoItem.getTitle());
            contentValues.put("description", todoItem.getDescription());
            contentValues.put("due_date", todoItem.getDueDate());
            contentValues.put("notes", todoItem.getNotes());

            sqLiteDatabase.insert(tableName, null, contentValues);
            Log.i(tag, " inserted item into database");
        } catch (Exception exception) {
            Log.i(tag, "" + exception);
        }

    }

    /* Method to update an existing to-do item within the Sqlite database by searching it on the id*/
    public void update(String id, TodoItem todoItem) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String title = todoItem.getTitle();
        String description = todoItem.getDescription();
        String dueDate = todoItem.getDueDate();
        String notes = todoItem.getNotes();

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", title);
            contentValues.put("description", description);
            contentValues.put("due_date", dueDate);
            contentValues.put("notes", notes);

            Log.i(tag, " updated item from database" + id);
            sqLiteDatabase.update(tableName, contentValues, "id = ?", new String[]{id});

        } catch (Exception exception) {
            Log.i(tag, "" + exception);
        }

    }

    /*Method to delete an existing to-do item from the sqlite database by searching it on the id*/
    public void delete(String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        try {
            sqLiteDatabase.delete("todo", "id = ?", new String[]{id});
            Log.i(tag, " delete item from database");
        } catch (Exception exception) {
            Log.i(tag, "" + exception);
        }


    }

    /*Method to get al existing to-do items from the sqlite database and returning them in an arraylist*/
    public ArrayList<TodoItem> getAll() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String getAllQuery = "SELECT * FROM todo;\n";
        ArrayList<TodoItem> loadedTodoItems = new ArrayList<>();

        Cursor cursor = null;
        if (sqLiteDatabase != null) {
            cursor = sqLiteDatabase.rawQuery(getAllQuery, null);
        } else {
            Log.e(tag, " database is empty or not created so couldn't receive data");
        }

        while (cursor != null && cursor.moveToNext()) {
            String id = cursor.getString(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);
            String due_date = cursor.getString(3);
            String notes = cursor.getString(4);

            TodoItem newTodoItem = new TodoItem(id, title, description, due_date, notes);
            loadedTodoItems.add(newTodoItem);
            Log.i(tag, " received item from database: " + newTodoItem.getTitle());
        }

        if (cursor != null) {
            cursor.close();
        }

        Log.i(tag, " received all items from database");
        return loadedTodoItems;
    }
}
