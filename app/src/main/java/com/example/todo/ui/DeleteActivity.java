package com.example.todo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.database.SqliteManager;

public class DeleteActivity extends AppCompatActivity {

    Button deleteButton;
    Button cancelButton;

    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            sqliteManager = new SqliteManager(getApplicationContext());
            sqliteManager.delete(getIntent().getStringExtra("id"));
            Intent intent = new Intent(DeleteActivity.this, ListActivity.class);
            startActivity(intent);
        });


        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(DeleteActivity.this, ListActivity.class);
            startActivity(intent);
        });
    }
}