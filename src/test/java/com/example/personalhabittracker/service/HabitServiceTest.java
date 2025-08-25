package com.example.personalhabittracker.service;

import com.example.personalhabittracker.model.Habit;
import com.example.personalhabittracker.repository.HabitRepository;
import com.example.personalhabittracker.repository.HabitCompletionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HabitServiceTest {
    @Test
    void testGetCompletedHabits() {
        HabitRepository habitRepo = Mockito.mock(HabitRepository.class);
        HabitCompletionRepository completionRepo = Mockito.mock(HabitCompletionRepository.class);
        Habit h1 = new Habit(); h1.setName("A"); h1.setCompleted(true);
        Habit h2 = new Habit(); h2.setName("B"); h2.setCompleted(false);
        Mockito.when(habitRepo.findAll()).thenReturn(Arrays.asList(h1, h2));
        HabitService service = new HabitService(habitRepo, completionRepo);
        List<Habit> completed = service.getCompletedHabits();
        assertEquals(1, completed.size());
        assertTrue(completed.get(0).isCompleted());
    }
}
