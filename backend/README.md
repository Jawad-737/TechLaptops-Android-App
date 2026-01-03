# PHP Backend Setup Instructions

## Prerequisites
- PHP 7.0 or higher
- MySQL/MariaDB database server
- Web server (Apache/Nginx) or PHP built-in server

## Setup Steps

### 1. Database Setup
1. Open MySQL command line or phpMyAdmin
2. Run the SQL script `create_table.sql` to create the database and users table:
   ```sql
   CREATE DATABASE IF NOT EXISTS users_db;
   USE users_db;
   
   CREATE TABLE IF NOT EXISTS users (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```

### 2. Configure Database Connection
Edit `config.php` and update the following constants with your MySQL credentials:
```php
define('DB_HOST', 'localhost');      // Your MySQL host
define('DB_USER', 'root');            // Your MySQL username
define('DB_PASS', '');                // Your MySQL password
define('DB_NAME', 'users_db');        // Your database name
```

### 3. Deploy PHP Files
- Upload `config.php` and `login.php` to your web server
- Make sure both files are in the same directory (e.g., `/backend/`)

### 4. Update Android App URL
In `login.java`, update the `LOGIN_URL` constant with your server's URL:
```java
private static final String LOGIN_URL = "http://your-server.com/backend/login.php";
```

For local testing (using Android Emulator), use:
```java
private static final String LOGIN_URL = "http://10.0.2.2/backend/login.php";
```

For local testing (using physical device on same network), use your computer's IP:
```java
private static final String LOGIN_URL = "http://192.168.1.XXX/backend/login.php";
```

### 5. Test the Backend
You can test the PHP endpoint using a web browser or curl:
```
http://your-server.com/backend/login.php?username=testuser&password=test123
```

Or using curl:
```bash
curl "http://your-server.com/backend/login.php?username=testuser&password=test123"
```

The response will be:
- "success" - if login is successful
- "failed" - if credentials are wrong
- "error" - if parameters are missing

## Security Notes
- In production, use HTTPS instead of HTTP
- Consider hashing passwords (use password_hash() in PHP)
- Add input validation and sanitization
- Implement rate limiting to prevent brute force attacks
- Use prepared statements (already implemented) to prevent SQL injection

