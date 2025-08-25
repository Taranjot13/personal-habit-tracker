package com.example.personalhabittracker.service;

import com.example.personalhabittracker.model.Habit;
import com.example.personalhabittracker.model.HabitCompletion;
import com.example.personalhabittracker.model.User;
import com.example.personalhabittracker.repository.HabitRepository;
import com.example.personalhabittracker.repository.HabitCompletionRepository;
import com.example.personalhabittracker.exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HabitService {
    private final HabitRepository habitRepository;
    private final HabitCompletionRepository habitCompletionRepository;

    public HabitService(HabitRepository habitRepository, HabitCompletionRepository habitCompletionRepository) {
        this.habitRepository = habitRepository;
        this.habitCompletionRepository = habitCompletionRepository;
    }

    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    public List<Habit> getCompletedHabits() {
        return habitRepository.findAll().stream()
                .filter(Habit::isCompleted)
                .toList();
    }

    public Habit getHabitById(Long id) {
        return habitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with id " + id));
    }

    public Habit createHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    public Habit updateHabit(Long id, Habit habitDetails) {
        Habit habit = getHabitById(id);
        habit.setName(habitDetails.getName());
        habit.setDescription(habitDetails.getDescription());
        habit.setCompleted(habitDetails.isCompleted());
        habit.setCategory(habitDetails.getCategory());
        habit.setTags(habitDetails.getTags());
        habit.setReminderTime(habitDetails.getReminderTime());
        habit.setReminderEnabled(habitDetails.isReminderEnabled());
        return habitRepository.save(habit);
    }

    public HabitCompletion markHabitCompletion(Long habitId, boolean completed, LocalDate date) {
        Habit habit = getHabitById(habitId);
        HabitCompletion hc = habitCompletionRepository.findByHabitAndDate(habit, date)
            .stream()
            .findFirst()
            .orElseGet(() -> {
                HabitCompletion completion = new HabitCompletion();
                completion.setHabit(habit);
                completion.setDate(date);
                return completion;
            });
        hc.setCompleted(completed);
        return habitCompletionRepository.save(hc);
    }

    public List<HabitCompletion> getCompletionsForHabit(Long habitId) {
        Habit habit = getHabitById(habitId);
        return habitCompletionRepository.findByHabit(habit);
    }

    public List<HabitCompletion> getCompletionsForUserAndDate(Long userId, LocalDate date) {
        return habitCompletionRepository.findByHabitUserIdAndDate(userId, date);
    }

    public int getCurrentStreak(Long habitId) {
        List<HabitCompletion> completions = getCompletionsForHabit(habitId);
        completions.sort(Comparator.comparing(HabitCompletion::getDate).reversed());
        int streak = 0;
        LocalDate today = LocalDate.now();
        for (HabitCompletion hc : completions) {
            if (hc.isCompleted() && (today.minusDays(streak).equals(hc.getDate()))) {
                streak++;
            } else if (!hc.isCompleted()) {
                break;
            }
        }
        return streak;
    }

    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }

    public List<Habit> getHabitsByUser(User user) {
        return habitRepository.findByUser(user);
    }

    public Habit addHabitForUser(User user, String name, String description) {
        Habit habit = new Habit();
        habit.setName(name);
        habit.setDescription(description);
        habit.setCompleted(false);
        habit.setUser(user);
        return habitRepository.save(habit);
    }
}
