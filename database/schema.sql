CREATE DATABASE notesdb;

USE notesdb;

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