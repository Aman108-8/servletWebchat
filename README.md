# Servlet WebChat 💬

A simple real-time style **chat web application** built using **Java Servlets, JSP, and MySQL**.  
This project demonstrates how to build a classic chat system from scratch using core Java EE concepts (Servlet + JSP) without any frameworks.

---

## 📌 Features
- **User Authentication**: Login system to identify users.
- **Friend Management**: Add/search friends and build a friend list.
- **Chat System**:
  - Send and receive messages between two users.
  - Messages stored in MySQL database for persistence.
  - Chat history loads when a user selects a friend.
- **UI Design**:
  - Sidebar with friends list.
  - Chat container with messages aligned left/right depending on sender.
  - Simple CSS styling for a modern look.
- **Session Handling**:
  - `HttpSession` used to store `userId`, `userName`, `friendId`, etc.
  - Prevents showing IDs in URL for better security.
- **Servlet Flow**:
  - `login` → `chat.jsp` → `chatOpen` → `DoChat`  
  - Uses both `RequestDispatcher.forward()` and `HttpServletResponse.sendRedirect()` appropriately to avoid resubmission issues.

---

## 🛠️ Technologies Used
- **Java 17+**
- **Jakarta Servlet API (Tomcat 10.1)**
- **JSP (Java Server Pages)**
- **MySQL 8.0**
- **JDBC** for database connectivity
- **NetBeans IDE** for project development
- **Git + GitHub** for version control


## ⚙️ Database Design (MySQL)
### `users` table
```sql
create database webchat;

use webchat;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE friendships (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userId INT NOT NULL,
    friendId INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_user FOREIGN KEY (userId) REFERENCES users(user_Id),
    CONSTRAINT fk_friend FOREIGN KEY (friendId) REFERENCES users(user_Id),

    UNIQUE (userId, friendId)  -- Prevent duplicate friend entries
);

CREATE TABLE chat_messages (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    message TEXT NOT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (sender_id) REFERENCES users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES users(user_id)
);
```

## ⚡ Setup & Installation

### 1. Clone the repository
```bash
git clone https://github.com/Aman108-8/servletWebchat.git
cd servletWebchat
```

### 2. tomcat installation and setup
2. Install Apache Tomcat

-Download Tomcat 10.1.x from Tomcat official site
-Extract and configure it in your IDE (NetBeans/IntelliJ/Eclipse).
-Add Tomcat as a server for your project so Servlets and JSP can run.

### 3. db MySQL JDBC Connector

- Download MySQL Connector/J (e.g., mysql-connector-j-8.0.33.jar) from MySQL site
- Copy the JAR file into: your-proj/Web Pages/Web-INF/lib

### 4. Enter your password
- In loginDB

<img width="1900" height="917" alt="image" src="https://github.com/user-attachments/assets/62f3dc73-436e-4430-8c3c-3004280ea9d2" />

<img width="1895" height="912" alt="image" src="https://github.com/user-attachments/assets/cd844762-d13a-47cc-8af1-9df14b64c722" />

<img width="1918" height="923" alt="image" src="https://github.com/user-attachments/assets/fde1d304-a983-4b71-af3e-630c7537792f" />

