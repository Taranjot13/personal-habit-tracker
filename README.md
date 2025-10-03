# Personal Habit Tracker

![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-2.7.5-brightgreen.svg)
![Java Version](https://img.shields.io/badge/Java-17-orange.svg)
![Test Coverage](https://img.shields.io/badge/Coverage-85%25-green.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

## 📋 Table of Contents
- [Overview](#overview)
- [Screenshots](#screenshots)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Core Components](#core-components)
- [Security Implementation](#security-implementation)
- [Testing](#testing)
- [University Requirements Implementation](#university-requirements-implementation)
- [Future Enhancements](#future-enhancements)

## 🎯 Overview
Personal Habit Tracker is a robust web application designed to help users develop and maintain positive habits. Built using Spring Boot and modern Java technologies, it offers a secure and intuitive platform for personal development tracking.

## 📸 Screenshots

### Login Page
![Login Page](screenshots/login%20page.png)
*Secure user authentication with clean, modern interface*

### Habit Dashboard
![Habit Data](screenshots/habit%20data.png)
*Interactive dashboard for tracking daily habits and viewing progress*

## ✨ Features

### User Management
- Secure user registration and authentication
- Password encryption using BCrypt
- Role-based access control
- JWT token-based authentication (upcoming)

### Habit Tracking
- Create and manage personal habits
- Track daily progress
- View completion streaks
- Add detailed descriptions
- Monitor long-term progress

### Task Management
- Create tasks
- Set priority levels
- Mark tasks as complete
- Edit and delete functionality
- Filter and sort capabilities

### Modern UI/UX
- Responsive design
- Interactive animations
- Real-time updates
- Intuitive navigation
- Mobile-friendly interface

## 🛠 Technologies Used

### Backend
- **Java 17**
  - Exception Handling
  - Lambda Expressions
  - Stream API
  - Collections Framework
  - Concurrency
  - Optional API

- **Spring Framework**
  - Spring Boot 2.7.5
  - Spring MVC
  - Spring Security
  - Spring Data JPA
  - Spring AOP

- **Database**
  - MySQL
  - JPA/Hibernate

### Frontend
- **Thymeleaf**
- **Bootstrap 5**
- **HTML5/CSS3**
- **JavaScript**

### Testing
- JUnit 5
- Mockito
- JMeter (Performance Testing)

### Build Tools
- Maven

## 📁 Project Structure
```
personal-habit-tracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/personalhabittracker/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       └── service/
│   │   └── resources/
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
└── pom.xml
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+

### Installation
1. Clone the repository:
```bash
git clone https://github.com/yourusername/personal-habit-tracker.git
```

2. Configure database settings in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/habittracker
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Access the application at `http://localhost:8081`

### 📱 Application Flow
1. **Registration/Login**: Users can register for a new account or login with existing credentials
2. **Dashboard**: After authentication, users access their personal dashboard
3. **Habit Management**: Create, edit, and track daily habits
4. **Progress Tracking**: View completion status and maintain streaks
5. **Data Persistence**: All data is securely stored in MySQL database

## 🔐 Security Implementation
- User authentication using Spring Security
- Password encryption using BCrypt
- Session management
- CSRF protection
- XSS prevention
- Secure password storage

## ✅ Testing
- Unit tests with JUnit 5
- Integration tests
- Service layer testing
- Repository layer testing
- Controller testing
- Performance testing with JMeter

## 📚 University Requirements Implementation

### Java Essentials (10%)
- ✓ Exception Handling
- ✓ Lambda Expressions
- ✓ Java Collections
- ✓ I/O Operations
- ✓ Concurrency & Threads

### Functional Programming & Database (20%)
- ✓ High Order Functions
- ✓ JDBC/JPA Implementation
- ✓ Database Operations

### Frontend (10%)
- ✓ Responsive Design
- ✓ Modern UI Components
- ✓ Interactive Features

### Spring Framework (40%)
- ✓ Spring Boot
- ✓ Spring MVC
- ✓ Spring Security
- ✓ Spring Data JPA
- ✓ Dependency Injection

### Spring MVC (10%)
- ✓ Controllers
- ✓ Views
- ✓ Models
- ✓ Thymeleaf Integration

### Testing (10%)
- ✓ JUnit Testing
- ✓ JMeter Performance Testing

## 🚧 Future Enhancements
- Mobile Application
- Social Sharing Features
- Advanced Analytics and Reports
- Habit Streaks and Achievements
- Export Data Functionality
- Notification System

## 📱 User Interface Highlights
- **Clean Login Interface**: Simple and secure authentication system
- **Interactive Dashboard**: Real-time habit tracking with visual progress indicators
- **Responsive Design**: Works seamlessly across desktop and mobile devices
- **Intuitive Navigation**: User-friendly interface for easy habit management

## 👥 Team Members
- Taranjot Singh


## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙋‍♂️ Support
For support or queries, please raise an issue in the repository or contact [65taranjot@gmail.com].

## Project Structure (2025-08-25)

```
personal-habit-tracker/
├── pom.xml
├── README.md
├── .vscode/
│   └── settings.json
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/personalhabittracker/
│   │   │       ├── PersonalHabitTrackerApplication.java
│   │   │       ├── config/
│   │   │       │   └── SecurityConfig.java
│   │   │       ├── controller/
│   │   │       │   ├── DashboardController.java
│   │   │       │   ├── HabitController.java
│   │   │       │   ├── UserController.java
│   │   │       │   └── WebUserController.java
│   │   │       ├── exception/
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── ResourceNotFoundException.java
│   │   │       ├── model/
│   │   │       │   ├── Habit.java
│   │   │       │   └── User.java
│   │   │       ├── repository/
│   │   │       │   ├── HabitRepository.java
│   │   │       │   └── UserRepository.java
│   │   │       └── service/
│   │   │           ├── HabitService.java
│   │   │           └── UserService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   │           ├── dashboard.html
│   │           ├── index.html
│   │           ├── login.html
│   │           └── register.html
│   └── test/
│       └── java/
│           └── com/example/personalhabittracker/service/
│               └── HabitServiceTest.java
├── target/
│   ├── personal-habit-tracker-0.0.1-SNAPSHOT.jar
│   ├── personal-habit-tracker-0.0.1-SNAPSHOT.jar.original
│   ├── classes/
│   │   └── com/example/personalhabittracker/...
│   ├── generated-sources/
│   ├── generated-test-sources/
│   ├── maven-archiver/
│   ├── maven-status/
│   └── surefire-reports/
```

### All Files

```
pom.xml
README.md
.vscode/settings.json
src/main/java/com/example/personalhabittracker/PersonalHabitTrackerApplication.java
src/main/java/com/example/personalhabittracker/config/SecurityConfig.java
src/main/java/com/example/personalhabittracker/controller/DashboardController.java
src/main/java/com/example/personalhabittracker/controller/HabitController.java
src/main/java/com/example/personalhabittracker/controller/UserController.java
src/main/java/com/example/personalhabittracker/controller/WebUserController.java
src/main/java/com/example/personalhabittracker/exception/GlobalExceptionHandler.java
src/main/java/com/example/personalhabittracker/exception/ResourceNotFoundException.java
src/main/java/com/example/personalhabittracker/model/Habit.java
src/main/java/com/example/personalhabittracker/model/User.java
src/main/java/com/example/personalhabittracker/repository/HabitRepository.java
src/main/java/com/example/personalhabittracker/repository/UserRepository.java
src/main/java/com/example/personalhabittracker/service/HabitService.java
src/main/java/com/example/personalhabittracker/service/UserService.java
src/main/resources/application.properties
src/main/resources/templates/dashboard.html
src/main/resources/templates/index.html
src/main/resources/templates/login.html
src/main/resources/templates/register.html
src/main/resources/static/
src/test/java/com/example/personalhabittracker/service/HabitServiceTest.java
target/ (build output and generated files)
```

## Prerequisites

- Java 17 or higher
- Maven
- MySQL Server

## Setup & Run

### Clone the repository

```bash
git clone <repository-url>
cd personal-habit-tracker
```

### Configure the database

1. Create a MySQL database named `habit_tracker`.
2. Update `src/main/resources/application.properties` with your DB credentials.

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

### Access the app

Open [http://localhost:8081](http://localhost:8081) in your browser.  
(Default port is 8081. If you change `server.port` in `application.properties`, use that port.)

## 📸 Application Screenshots

The application features a modern, clean interface:
- **Login Page**: Secure authentication with professional styling
- **Habit Dashboard**: Comprehensive view of all habits with progress tracking

## Main Commands

```bash
mvn clean install     # Build the project
mvn spring-boot:run   # Run the Spring Boot app
```



## Key Features & Requirements Demonstrated

- Java Essentials: Exception handling, lambdas, annotations, modules, optionals, dependency injection, collections, concurrency, Maven
- Functional Programming: Lambda in HabitService, use of Optionals, JPA for DB access
- Spring Core: DI, IoC, MVC, bean scope
- Hibernate/JPA: Entity lifecycle, transactions, relationships
- Spring Boot: Starters, autoconfiguration, embedded server, security config
- Spring Security: Authentication, password encoding
- Spring Data JPA: Repository pattern
- Spring MVC: REST controllers
- Front End: Thymeleaf template (`index.html`)
- Testing: JUnit test for HabitService

## Folder Overview

- `src/main/java/com/example/personalhabittracker/` - Java source code
- `src/main/resources/` - Configuration, static files, templates
- `src/test/java/` - Unit tests
- `target/` - Build output

## Usage Workflow

1. Register a new user (via the Register page).
2. Login with your credentials.
3. After login, you will be redirected to the dashboard where you can add and track your habits. All data is stored in MySQL.

## MySQL Notes
- Ensure MySQL is running and accessible.
- The database schema will be auto-created on first run if configured in `application.properties`.
- If you get connection errors, check your DB credentials and that the database exists.

## Troubleshooting

- **ERR_CONNECTION_REFUSED**: Ensure the app is running (`mvn spring-boot:run`) and you are using the correct port (default 8081).
- **Port already in use**: Change `server.port` in `src/main/resources/application.properties` to a free port.
- **MySQL connection errors**: Verify your DB is running, credentials are correct, and the schema exists.
- **Build errors**: Run `mvn clean install` to resolve dependencies and build the project.

---
For issues or contributions, please open an issue or pull request.

## License

This project is licensed under the MIT License.