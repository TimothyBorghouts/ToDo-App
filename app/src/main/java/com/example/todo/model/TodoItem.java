package com.example.todo.model;

public class TodoItem {
    private String id;
    private final String title;
    private final String description;
    private final String dueDate;
    private final String notes;

    public TodoItem(String id, String title, String description, String dueDate, String notes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.notes = notes;
    }

    public TodoItem(String title, String description, String dueDate, String notes) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getNotes() {
        return notes;
    }
}
