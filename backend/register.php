<?php
require_once 'config.php';

// Get all data from URL parameters
$fname = $_GET['fname'] ?? '';
$lname = $_GET['lname'] ?? '';
$username = $_GET['username'] ?? '';
$password = $_GET['password'] ?? '';

// Check if all fields are provided
if (empty($fname) || empty($lname) || empty($username) || empty($password)) {
    echo "error";
    exit;
}

// Get database connection
$conn = getConnection();

// Check if username already exists
$checkStmt = $conn->prepare("SELECT * FROM users WHERE username = ?");
$checkStmt->bind_param("s", $username);
$checkStmt->execute();
$result = $checkStmt->get_result();

if ($result->num_rows > 0) {
    // Username already exists
    echo "exists";
    $checkStmt->close();
    $conn->close();
    exit;
}

// Insert new user
$insertStmt = $conn->prepare("INSERT INTO users (username, password, first_name, last_name) VALUES (?, ?, ?, ?)");
$insertStmt->bind_param("ssss", $username, $password, $fname, $lname);

if ($insertStmt->execute()) {
    // Registration successful
    echo "success";
} else {
    // Registration failed
    echo "failed";
}

$insertStmt->close();
$checkStmt->close();
$conn->close();
?>

