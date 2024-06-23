package com.example.task_trove;

public class Task {
    private final String name;
    private final String description;
    private final String date;
    private final String category;
    private int status;

    public Task(String name, String description, String date, String category, int status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.category = category;
        this.status = status;
    }
    public Task(String name, String description, String date, int status) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.category = "None";
        this.status = status;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public int getStatus() { return status; }
    public void setStatus(int task_status) { status = task_status; }
    public String getCategory(){return category; }

}