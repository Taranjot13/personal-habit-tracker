package com.example.personalhabittracker.controller;

import com.example.personalhabittracker.dto.TaskDTO;
import com.example.personalhabittracker.model.Task;
import com.example.personalhabittracker.model.User;
import com.example.personalhabittracker.service.TaskService;
import com.example.personalhabittracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private static final String REDIRECT_TASKS = "redirect:/tasks";
    private static final String SUCCESS_MESSAGE = "success";
    private static final String ERROR_MESSAGE = "error";

    private final TaskService taskService;
    private final UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public String listTasks(Model model) {
        Long currentUserId = getCurrentUserId();
        model.addAttribute("tasks", taskService.getUserTasks(currentUserId));
        model.addAttribute("newTask", new TaskDTO());
        return "tasks/list";
    }

    @PostMapping
    public String createTask(@ModelAttribute TaskDTO taskDTO, RedirectAttributes redirectAttributes) {
        try {
            Task task = convertToTask(taskDTO);
            taskService.createTask(getCurrentUserId(), task);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, "Task created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Failed to create task: " + e.getMessage());
        }
        return REDIRECT_TASKS;
    }

    @PostMapping("/{taskId}/toggle")
    public String toggleTaskCompletion(@PathVariable Long taskId, RedirectAttributes redirectAttributes) {
        try {
            Long currentUserId = getCurrentUserId();
            taskService.toggleTaskCompletion(taskId, currentUserId);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, "Task status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Failed to update task status: " + e.getMessage());
        }
        return REDIRECT_TASKS;
    }

    @GetMapping("/{taskId}/edit")
    public String showEditForm(@PathVariable Long taskId, Model model) {
        Long currentUserId = getCurrentUserId();
        Task task = taskService.getTask(currentUserId, taskId);
        model.addAttribute("task", convertToDTO(task));
        return "tasks/edit";
    }

    @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable Long taskId, @ModelAttribute TaskDTO taskDTO, 
                           RedirectAttributes redirectAttributes) {
        try {
            Long currentUserId = getCurrentUserId();
            Task task = convertToTask(taskDTO);
            task.setUser(userService.findById(currentUserId).orElseThrow(() -> 
                new IllegalStateException("User not found")));
            taskService.updateTask(taskId, task);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, "Task updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Failed to update task: " + e.getMessage());
        }
        return REDIRECT_TASKS;
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId, RedirectAttributes redirectAttributes) {
        try {
            Long currentUserId = getCurrentUserId();
            taskService.deleteTask(currentUserId, taskId);
            redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE, "Task deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(ERROR_MESSAGE, "Failed to delete task: " + e.getMessage());
        }
        return REDIRECT_TASKS;
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetails springUser) {
            return userService.findByUsername(springUser.getUsername())
                .map(User::getId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
        }
        throw new IllegalStateException("User not authenticated");
    }

    private Task convertToTask(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedAt(task.getCreatedAt());
        if (task.getUser() != null) {
            dto.setUsername(task.getUser().getUsername());
            dto.setUserId(task.getUser().getId());
        }
        return dto;
    }
}
