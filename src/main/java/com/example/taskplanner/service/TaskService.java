package com.example.taskplanner.service;

import com.example.taskplanner.model.Task;
import com.example.taskplanner.model.User;
import com.example.taskplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksForUser(User user) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getAssignedUser().equals(user))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getPriority().equals(priority))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByAdminPriority(AdminPriority adminPriority) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getAdminPriority().equals(adminPriority))
                .collect(Collectors.toList());
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}

