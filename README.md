# Java Project — Spring Boot + React

A full-stack web application being developed using **Spring Boot (Java)** for the backend, **React + Vite** for the frontend, and **MySQL** for database storage.

## Current Project Status

The project is currently at the **foundation/setup stage**.

Completed:

- Spring Boot backend created with Maven.
- React + Vite frontend created.
- Git repository initialized and connected to GitHub.
- Login UI created.
- Register UI created.
- React Router configured.
- MySQL 8.4 configured inside WSL Ubuntu.
- `java_project` database created.
- `users` table created.
- Spring Boot successfully connected to MySQL.
- Database password moved from hard-coded configuration to the `DB_PASSWORD` environment variable.

The application is **not yet connected end-to-end**: the Login/Register React forms do not currently save or authenticate users through the backend.

---

## Tech Stack

### Backend

- Java
- Spring Boot
- Maven
- Spring Web
- Spring Data JPA
- MySQL Driver
- Spring Security
- Validation

Base package:

```text
com.example.java_project
```

Backend structure:

```text
src/main/java/com/example/java_project/
├── config/
├── controller/
├── dto/
├── exception/
├── model/
├── repository/
├── service/
└── JavaProjectApplication.java
```

### Frontend

- React
- Vite
- JavaScript
- React Router DOM

Frontend structure:

```text
frontend/src/
├── assets/
├── components/
├── pages/
│   ├── Login.jsx
│   └── Register.jsx
├── services/
├── styles/
│   ├── Login.css
│   └── Register.css
├── App.jsx
└── main.jsx
```

Frontend development server:

```text
http://localhost:5173
```

Backend server:

```text
http://localhost:8080
```

---

# Database

MySQL is running inside **WSL Ubuntu**, not as a Windows installation.

Database:

```text
java_project
```

Current table:

```text
users
├── id
├── name
├── email
└── password
```

Table definition:

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
```

The `email` column is unique to prevent duplicate accounts.

---

# Spring Boot Database Configuration

`src/main/resources/application.properties` currently uses:

```properties
spring.application.name=java-project

spring.datasource.url=jdbc:mysql://localhost:3306/java_project
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## Important: Database Password

**Do not put the actual MySQL password in `application.properties` or commit it to GitHub.**

The application expects this environment variable:

```text
DB_PASSWORD
```

In the WSL terminal:

```bash
export DB_PASSWORD='YOUR_MYSQL_PASSWORD'
```

Verify without displaying the password:

```bash
if [ -n "$DB_PASSWORD" ]; then echo "DB_PASSWORD is set"; else echo "DB_PASSWORD is NOT set"; fi
```

Expected:

```text
DB_PASSWORD is set
```

> The environment variable created with `export` applies to the current WSL shell/session. If a new terminal is opened, set it again or configure it through a secure local environment mechanism.

---

# Running the Backend

From the project root:

```bash
cd /mnt/e/java-project/java-project
```

Make sure `DB_PASSWORD` is set:

```bash
export DB_PASSWORD='YOUR_MYSQL_PASSWORD'
```

Start Spring Boot:

```bash
./mvnw spring-boot:run
```

A successful startup should contain messages similar to:

```text
Database JDBC URL [jdbc:mysql://localhost:3306/java_project]
Database version: 8.4.11
Tomcat started on port 8080
Started JavaProjectApplication
```

The backend is then available on:

```text
http://localhost:8080
```

---

# Running the Frontend

Open another terminal:

```bash
cd /mnt/e/java-project/java-project/frontend
```

Install dependencies if needed:

```bash
npm.cmd install
```

Start Vite:

```bash
npm.cmd run dev
```

The frontend should be available at:

```text
http://localhost:5173
```

On this Windows setup, `npm.cmd` is used because PowerShell may block the `npm.ps1` script.

---

# Current Frontend Pages

## Login

Route:

```text
/
```

File:

```text
frontend/src/pages/Login.jsx
```

The page currently contains:

- Email
- Password
- Login button
- Link to Register

## Register

Route:

```text
/register
```

File:

```text
frontend/src/pages/Register.jsx
```

The page currently contains:

- Name
- Email
- Password
- Confirm password
- Register button
- Link to Login

These are currently **UI only**. They are not yet connected to backend APIs.

---

# What Has NOT Been Implemented Yet

The next teammate should continue from here.

Recommended implementation order:

```text
1. User JPA Entity
       ↓
2. User Repository
       ↓
3. DTOs
       ↓
4. User Service
       ↓
5. REST Controller
       ↓
6. Registration API
       ↓
7. Connect React Register form to API
       ↓
8. Password hashing
       ↓
9. Login API
       ↓
10. Spring Security authentication
       ↓
11. Connect React Login form
       ↓
12. Dashboard / protected routes
       ↓
13. Validation and exception handling
       ↓
14. Testing
```

## Immediate Next Task

Create:

```text
src/main/java/com/example/java_project/model/User.java
```

Map it to the existing MySQL `users` table using JPA.

The entity should correspond to:

```text
id       → BIGINT
name     → VARCHAR(100)
email    → VARCHAR(150)
password → VARCHAR(255)
```

After that, create the repository and service layers before connecting the React forms.

---

# Spring Security Note

Spring Boot currently prints a generated development security password such as:

```text
Using generated security password: ...
```

This is expected because Spring Security has been included but custom authentication has **not been implemented yet**.

Do not use that generated password as the application's final login system.

The teammate implementing authentication should replace the default Spring Security configuration with the project's actual user authentication flow.

---

# Git / Collaboration

GitHub repository:

```text
https://github.com/utkarshbuilds/java-project.git
```

Main branch:

```text
main
```

Important commits already made include:

```text
Initial Spring Boot project
Set up React frontend structure
Create and style login page
Connect login page to React app
Add register page and React routing
```

Before making changes:

```bash
git pull origin main
```

After completing a logical task:

```bash
git status
git add .
git commit -m "Describe the change"
git push origin main
```

Before committing, make sure no real passwords, API keys, or other secrets are being committed.

---

# Current Architecture

```text
                 React + Vite
                localhost:5173
                       │
                       │ HTTP / REST API
                       ▼
                Spring Boot
                localhost:8080
                       │
                       │ JPA / JDBC
                       ▼
              MySQL 8.4 in WSL
                localhost:3306
                       │
                       ▼
                 java_project
                       │
                       ▼
                     users
```

---

# Handoff Summary

The previous developer completed the **project setup, frontend authentication UI, database creation, and Spring Boot ↔ MySQL connection**.

The most important point for the next developer:

> **Do not recreate the project or database. Continue from the existing codebase.**

The database already exists, Spring Boot can connect to it, and the React Login/Register pages already exist.

The next implementation task is the **JPA User Entity**, followed by the backend API and frontend integration.
