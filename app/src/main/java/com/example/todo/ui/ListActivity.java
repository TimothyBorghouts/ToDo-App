package com.example.todo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todo.R;
import com.example.todo.database.SqliteManager;
import com.example.todo.model.TodoItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public static final String tag = "ListActivity";

    RecyclerView recyclerView;
    FloatingActionButton addButton;

    SqliteManager sqliteManager;
    ArrayList<TodoItem> todoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        sqliteManager = new SqliteManager(this);

        recyclerView = findViewById(R.id.recyclerview);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(tag, " Clicked on add button and go to Add Activity");
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        //todoItems = sqliteManager.getAll();
    }
}