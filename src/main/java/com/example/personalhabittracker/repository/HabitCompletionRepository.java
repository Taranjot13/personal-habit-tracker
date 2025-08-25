package com.example.personalhabittracker.repository;

import com.example.personalhabittracker.model.HabitCompletion;
import com.example.personalhabittracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, Long> {
    List<HabitCompletion> findByHabit(Habit habit);
    List<HabitCompletion> findByHabitAndDate(Habit habit, LocalDate date);
    List<HabitCompletion> findByHabitUserIdAndDate(Long userId, LocalDate date);
}
