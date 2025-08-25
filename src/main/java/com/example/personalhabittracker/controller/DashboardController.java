package com.example.personalhabittracker.controller;

import com.example.personalhabittracker.model.Habit;
import com.example.personalhabittracker.model.User;
import com.example.personalhabittracker.service.HabitService;
import com.example.personalhabittracker.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DashboardController {
    private final HabitService habitService;
    private final UserService userService;

    public DashboardController(HabitService habitService, UserService userService) {
        this.habitService = habitService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // Bypass authentication for now
        List<Habit> habits = habitService.getAllHabits(); // Show all habits or empty list
        model.addAttribute("habits", habits);
        return "dashboard";
    }

    @PostMapping("/habits/add")
    public String addHabit(@RequestParam String name,
                           @RequestParam(required = false) String description) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username).orElse(null);
        habitService.addHabitForUser(user, name, description);
        return "redirect:/dashboard";
    }
    @PostMapping("/habits/delete/{id}")
    public String deleteHabit(@org.springframework.web.bind.annotation.PathVariable("id") Long id) {
        habitService.deleteHabit(id);
        return "redirect:/dashboard";
    }
}
