
package com.example.personalhabittracker.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
public class Habit {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private boolean completed;
    private String category; // For habit categories

    @ElementCollection
    private Set<String> tags; // For tags

    private LocalTime reminderTime; // For reminders

    private boolean reminderEnabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<HabitCompletion> completions;

    // Constructors, getters and setters

    public Habit() {}
    // Default constructor required by JPA

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Set<String> getTags() { return tags; }
    public void setTags(Set<String> tags) { this.tags = tags; }

    public LocalTime getReminderTime() { return reminderTime; }
    public void setReminderTime(LocalTime reminderTime) { this.reminderTime = reminderTime; }

    public boolean isReminderEnabled() { return reminderEnabled; }
    public void setReminderEnabled(boolean reminderEnabled) { this.reminderEnabled = reminderEnabled; }

    public List<HabitCompletion> getCompletions() { return completions; }
    public void setCompletions(List<HabitCompletion> completions) { this.completions = completions; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
