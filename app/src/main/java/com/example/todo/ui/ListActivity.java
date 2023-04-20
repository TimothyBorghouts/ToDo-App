package com.example.todo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(tag, " Clicked on add button and go to Add Activity");
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        sqliteManager = new SqliteManager(this);
        //todoItems = sqliteManager.getAll();
        TodoItem newTodoItem = new TodoItem("titeltje", "beschrijvinkje", 2, "23/02/2334", "Dit zijn mijn notities");
        todoItems.add(newTodoItem);

        recyclerviewAdapter = new RecyclerviewAdapter(todoItems);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerviewAdapter);
    }
}