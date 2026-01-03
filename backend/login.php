<?php
require_once 'config.php';

// Get username and password from URL parameters
$username = $_GET['username'];
$password = $_GET['password'];

// Check if username and password are provided
if (empty($username) || empty($password)) {
    echo "error";
    exit;
}

// Get database connection
$conn = getConnection();

// Check user credentials (using prepared statement for safety)
$stmt = $conn->prepare("SELECT * FROM users WHERE username = ? AND password = ?");
$stmt->bind_param("ss", $username, $password);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    // Login successful
    echo "success";
} else {
    // Login failed
    echo "failed";
}

$stmt->close();
$conn->close();
?>

