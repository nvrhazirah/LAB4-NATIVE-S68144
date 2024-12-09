package com.example.roomdatabasedemo;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final TaskRepository repository;

    public TaskViewModel(Application application) {
        super(application);
        repository = new TaskRepository(application);
    }

    public LiveData<List<Task>> getTasksForUser(int userId) {
        return repository.getTasksForUser(userId);
    }

    public void insert(Task task) {
        repository.insert(task);
    }
}
