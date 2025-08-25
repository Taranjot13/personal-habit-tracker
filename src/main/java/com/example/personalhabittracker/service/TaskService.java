package com.example.personalhabittracker.service;

import com.example.personalhabittracker.exception.ResourceNotFoundException;
import com.example.personalhabittracker.model.Task;
import com.example.personalhabittracker.model.User;
import com.example.personalhabittracker.repository.TaskRepository;
import com.example.personalhabittracker.repository.UserRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Task createTask(Long userId, Task task) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task taskDetails) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        
        // Ensure the task belongs to the current user
        if (!task.getUser().getId().equals(taskDetails.getUser().getId())) {
            throw new IllegalStateException("Not authorized to update this task");
        }
        
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        task.setDueDate(taskDetails.getDueDate());
        
        return taskRepository.save(task);
    }

    public void deleteTask(Long userId, Long taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
            
        // Ensure the task belongs to the current user
        if (!task.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Not authorized to delete this task");
        }
        taskRepository.delete(task);
    }

    public Task getTask(Long userId, Long taskId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
            
        // Ensure the task belongs to the current user
        if (!task.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Not authorized to view this task");
        }
        return task;
    }

    public List<Task> getUserTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public List<Task> getUserTasksByStatus(Long userId, boolean completed) {
        return taskRepository.findByUserIdAndCompleted(userId, completed);
    }

    public Task toggleTaskCompletion(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
            
        // Ensure the task belongs to the current user
        if (!task.getUser().getId().equals(userId)) {
            throw new IllegalStateException("Not authorized to modify this task");
        }
        
        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }
}
