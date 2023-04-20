package com.example.todo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.todo.R;
import com.example.todo.database.SqliteManager;
import com.example.todo.model.TodoItem;

public class AddActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Spinner prioritySpinner;
    private DatePicker dueDatePicker;
    private EditText notesEditText;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titleEditText = findViewById(R.id.title_edittext);
        descriptionEditText = findViewById(R.id.description_edittext);
        prioritySpinner = findViewById(R.id.priority_spinner);
        dueDatePicker = findViewById(R.id.due_datepicker);
        notesEditText = findViewById(R.id.notes_edittext);

        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
                Intent intent = new Intent(AddActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveItem() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        int priority = 2;
        int year = dueDatePicker.getYear();
        int month = dueDatePicker.getMonth() + 1;
        int day = dueDatePicker.getDayOfMonth();
        String dueDate = year + "-" + month + "-" + day;
        String notes = notesEditText.getText().toString();

        TodoItem newTodoItem = new TodoItem(title, description, priority, dueDate, notes);
        SqliteManager sqliteManager = new SqliteManager(this);
        sqliteManager.insert(newTodoItem);
    }
}