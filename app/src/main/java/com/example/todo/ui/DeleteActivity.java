package com.example.todo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqliteManager = new SqliteManager(getApplicationContext());
                sqliteManager.delete(getIntent().getStringExtra("id"));
                Intent intent = new Intent(DeleteActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });



        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteActivity.this, UpdateActivity.class);
                startActivity(intent);
            }
        });
    }
}