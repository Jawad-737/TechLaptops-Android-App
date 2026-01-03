-- SQL script to create the users table in MySQL
-- Run this script in your MySQL database before using the application

CREATE DATABASE IF NOT EXISTS users_db;
USE users_db;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Example: Insert a test user (password: test123)
-- INSERT INTO users (username, password) VALUES ('testuser', 'test123');

