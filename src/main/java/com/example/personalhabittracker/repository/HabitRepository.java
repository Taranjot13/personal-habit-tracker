package com.example.personalhabittracker.repository;

import com.example.personalhabittracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.personalhabittracker.model.User;
import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {
	List<Habit> findByUser(User user);
}
