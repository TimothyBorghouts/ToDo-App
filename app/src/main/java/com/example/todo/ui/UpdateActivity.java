package com.example.todo.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.todo.R;
import com.example.todo.database.SqliteManager;
import com.example.todo.model.TodoItem;

public class UpdateActivity extends AppCompatActivity {

    SqliteManager sqliteManager;

    String id, title, description, dueDate, notes;
    private EditText titleUpdateEditText;
    private EditText descriptionUpdateEditText;
    private DatePicker dueUpdateDatePicker;
    private EditText notesUpdateEditText;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleUpdateEditText = findViewById(R.id.title_update_edittext);
        descriptionUpdateEditText = findViewById(R.id.description_update_edittext);
        dueUpdateDatePicker = findViewById(R.id.due_update_datepicker);
        notesUpdateEditText = findViewById(R.id.notes_update_edittext);

        getIntentData();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleUpdateEditText.getText().toString().trim();
                String description = descriptionUpdateEditText.getText().toString().trim();
                int year = dueUpdateDatePicker.getYear();
                int month = dueUpdateDatePicker.getMonth() + 1;
                int day = dueUpdateDatePicker.getDayOfMonth();
                String dueDate = year + "-" + month + "-" + day;
                String notes = notesUpdateEditText.getText().toString().trim();

                TodoItem updatedToDoItem = new TodoItem(title, description, dueDate, notes);

                sqliteManager = new SqliteManager(getApplicationContext());
                sqliteManager.update(getIntent().getStringExtra("id"), updatedToDoItem);

                Intent intent = new Intent(UpdateActivity.this, ListActivity.class);
                startActivity(intent);

            }
        });
    }

    public void getIntentData(){
        if(getIntent().hasExtra("title")){
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");
            dueDate = getIntent().getStringExtra("dueDate");
            notes = getIntent().getStringExtra("notes");

            String[] dayMonthyear = dueDate.split("-");
            int year = Integer.parseInt(dayMonthyear[0]);
            int month = Integer.parseInt(dayMonthyear[1]) - 1;
            int day = Integer.parseInt(dayMonthyear[2]);

            titleUpdateEditText.setText(title);
            descriptionUpdateEditText.setText(description);
            dueUpdateDatePicker.updateDate(year, month, day);
            notesUpdateEditText.setText(notes);

        }
    }
}