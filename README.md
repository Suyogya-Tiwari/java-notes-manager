# 📝 Password-Protected Notes Application (Java)

## 📌 Project Overview

This is a **desktop-based Notes Application** built using **Java (Swing + JDBC)** that allows users to securely store and manage personal notes.

The application includes **user authentication (login/register)** and ensures that each user's notes are private and accessible only after successful login.

---

## 🚀 Features

### 🔐 Authentication

* User Registration
* Secure Login System
* Passwords stored using **SHA-256 hashing**

### 📝 Notes Management

* Add new notes
* View notes in a clean UI
* Edit notes (double-click)
* Delete notes
* Search notes (real-time filtering)

### 🎨 User Interface

* Built using **Java Swing**
* Card-style note display
* Clean and user-friendly layout
* Scrollable content view
* Popup dialogs for interaction

---

## 🛠️ Technologies Used

* **Java (Core + OOP concepts)**
* **Swing (GUI Development)**
* **JDBC (Database Connectivity)**
* **MySQL (Relational Database)**
* **SHA-256 (Password Security)**

---

## 🧠 Concepts Covered

* Object-Oriented Programming (Classes, Objects)
* Exception Handling (try-catch)
* JDBC (PreparedStatement, ResultSet)
* Swing Components (JFrame, JPanel, JList, JButton, JOptionPane)
* Event Handling
* MVC Architecture (basic separation)
* Multithreading (optional enhancements)
* Serialization (optional extension)

---

## 🗄️ Database Setup

### Step 1: Create Database

```sql
CREATE DATABASE notesdb;
USE notesdb;
```

### Step 2: Create Tables

```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password_hash VARCHAR(255)
);

CREATE TABLE notes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    title VARCHAR(100),
    content TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

---

## ⚙️ Setup Instructions

### 1. Clone / Download Project

* Download ZIP or clone repository

### 2. Open in IntelliJ IDEA

* Open project folder
* Ensure JDK 17+ is configured

### 3. Add JDBC Driver

* Download **MySQL Connector/J**
* Add `.jar` file via:

  * `File → Project Structure → Libraries → +`

### 4. Configure Database Connection

Edit `DBConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/notesdb";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### 5. Run the Application

* Open `Main.java`
* Click **Run ▶**

---

## ▶️ How to Use

1. Register a new account
2. Login using credentials
3. Add notes using **Add button**
4. Click on a note to select it
5. Use:

   * **View** → Read full note
   * **Double-click** → Edit note
   * **Delete** → Remove note
6. Use search bar to filter notes

---

## 📂 Project Structure

```
notesapp/
│
├── model/        → Data classes (User, Note)
├── view/         → UI (Login, Register, Dashboard)
├── controller/   → Business logic
├── util/         → DB connection & hashing
└── Main.java     → Entry point
```

---

## 🔒 Security Note

* Passwords are **never stored in plain text**
* SHA-256 hashing is used before saving to database

---

## ⚠️ Known Limitations

* Basic UI (can be further enhanced)
* No cloud backup
* No encryption for note content (only password protected)

---

## 🌟 Future Enhancements

* Dark mode toggle 🌙
* Note encryption 🔐
* Tags / categories 🏷️
* Export notes 📤
* Cloud sync ☁️
* Improved UI (Google Keep style)

---

## 👨‍💻 Author

**Suyogya**

---

## 📌 Conclusion

This project demonstrates the integration of:

* GUI development
* Database connectivity
* Secure authentication
* Clean application design

It is a complete example of a **Java-based desktop application using real-world concepts**.

---
