package com.example.todo.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.adapter.RecyclerviewAdapter;
import com.example.todo.database.SqliteManager;
import com.example.todo.model.TodoItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public static final String tag = "ListActivity";

    RecyclerView recyclerView;
    RecyclerviewAdapter recyclerviewAdapter;

    FloatingActionButton addButton;

    SqliteManager sqliteManager;
    ArrayList<TodoItem> todoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerview);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> {
            Log.i(tag, " Clicked on add button and go to Add Activity");
            Intent intent = new Intent(ListActivity.this, AddActivity.class);
            startActivity(intent);
        });

        sqliteManager = new SqliteManager(this);
        todoItems = new ArrayList<>();
        todoItems = sqliteManager.getAll();

        recyclerviewAdapter = new RecyclerviewAdapter(todoItems, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        }
    }
}