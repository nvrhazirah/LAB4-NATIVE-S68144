package com.example.roomdatabasedemo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {
    private final TaskDao taskDao;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public TaskRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        taskDao = db.taskDao();
    }

    public LiveData<List<Task>> getTasksForUser(int userId) {
        return taskDao.getTasksForUser(userId);
    }

    public void insert(Task task) {
        executorService.execute(() -> taskDao.insert(task));
    }
}
