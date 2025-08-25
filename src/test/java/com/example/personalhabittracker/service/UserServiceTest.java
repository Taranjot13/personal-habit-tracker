package com.example.personalhabittracker.service;

import com.example.personalhabittracker.model.User;
import com.example.personalhabittracker.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    // Testing with Lambda Expressions and Optional
    @Test
    void registerUser_ShouldCreateNewUser() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");

        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act - Using Lambda
        User savedUser = Stream.of(user)
            .peek(u -> u.setPassword(passwordEncoder.encode(u.getPassword())))
            .findFirst()
            .map(u -> userService.registerUser(u))
            .orElseThrow();

        // Assert
        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUsername());
        verify(userRepository).save(any(User.class));
    }

    // Testing with Concurrency
    @Test
    void concurrentUserRegistration_ShouldHandleMultipleUsers() {
        // Arrange
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");

        when(userRepository.save(any(User.class))).thenReturn(user1, user2);

        // Act - Using CompletableFuture for concurrent operations
        CompletableFuture<User> future1 = CompletableFuture.supplyAsync(() -> 
            userService.registerUser(user1));
        CompletableFuture<User> future2 = CompletableFuture.supplyAsync(() -> 
            userService.registerUser(user2));

        // Assert
        CompletableFuture.allOf(future1, future2).join();
        assertDoesNotThrow(() -> {
            User result1 = future1.get();
            User result2 = future2.get();
            assertEquals("user1", result1.getUsername());
            assertEquals("user2", result2.getUsername());
        });
    }

    // Testing with Optional and Exception Handling
    @Test
    void findByUsername_ShouldReturnOptionalUser() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act - Using Optional
        Optional<User> foundUser = userService.findByUsername(username);

        // Assert
        assertTrue(foundUser.isPresent());
        foundUser.ifPresent(u -> assertEquals(username, u.getUsername()));
    }

    // Demonstrating Collection Operations
    @Test
    void userOperations_ShouldHandleCollections() {
        // Using Java Collections and Stream API
        var users = Stream.of("user1", "user2", "user3")
            .map(name -> {
                User user = new User();
                user.setUsername(name);
                return user;
            })
            .toList();

        when(userRepository.findAll()).thenReturn(users);

        // Act & Assert
        assertDoesNotThrow(() -> {
            var result = users.stream()
                .map(User::getUsername)
                .filter(name -> name.startsWith("user"))
                .sorted()
                .toList();
            assertEquals(3, result.size());
        });
    }
}
