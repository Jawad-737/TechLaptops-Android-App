# TechLaptops - Android E-Commerce App

A simple Android e-commerce application for selling laptops with user authentication and shopping cart functionality.

## Features

- **User Authentication**: Login and registration system
- **Product Catalog**: Browse laptop products with details
- **Shopping Cart**: Add/remove items, view total price
- **Product Details**: View detailed information about each laptop
- **Local Data Storage**: SharedPreferences for cart persistence

## Technical Implementation

### Core Concepts Used
- **Activities**: Multiple screens with proper lifecycle management
- **Intents**: Navigation and data passing between activities
- **Custom Adapters**: ListView with BaseAdapter and ViewHolder pattern
- **SharedPreferences**: Local data persistence
- **Manual JSON Formatting**: String-based data serialization
- **Volley Library**: HTTP networking for authentication
- **Material Design**: Modern UI components

## Repository Structure
```
FInal/
├── app/                    # Android application
│   └── src/main/java/...   # Java source code
├── backend/                # PHP backend files
│   ├── config.php          # Database configuration
│   ├── create_table.sql    # Database setup
│   ├── login.php           # Login endpoint
│   └── register.php        # Registration endpoint
├── .gitignore              # Git ignore file
├── README.md               # This file
└── build.gradle.kts        # Gradle configuration
```

### Key Features Implementation

#### Data Storage
- **Cart Management**: Manual JSON formatting with SharedPreferences
- **Data Passing**: Intent extras for passing laptop data between activities
- **Local Persistence**: SharedPreferences for cart items

#### Networking
- **Authentication**: Volley HTTP requests to backend
- **Error Handling**: Comprehensive error management
- **URL Encoding**: Proper parameter encoding

#### UI/UX
- **Responsive Design**: Edge-to-edge display support
- **Navigation**: Toolbar with cart badge
- **User Feedback**: Toast messages and validation

## Requirements

- **Android Studio** Arctic Fox or later
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 36
- **Java**: Version 11

## Dependencies

- AndroidX AppCompat
- Material Design Components
- ConstraintLayout
- Volley (Networking)

## Setup Instructions

### Android App
1. Clone this repository
2. Open in Android Studio
3. Sync Gradle dependencies
4. Update server IP in `login.java` and `register.java`
5. Run the application

### Backend Setup
1. Set up MySQL database
2. Import `backend/create_table.sql`
3. Update database credentials in `backend/config.php`
4. Deploy PHP files to web server
5. Update Android app with your server URL

## Notes

- Backend PHP files included in `backend/` folder
- App uses hardcoded IP address for server connection (update in `login.java` and `register.java`)
- Cart data persists locally using SharedPreferences
- No external JSON libraries used - manual string parsing implemented
- See `backend/README.md` for server setup instructions

## Course Concepts Demonstrated

This project demonstrates fundamental Android development concepts:
- Activity lifecycle and navigation
- Custom adapters and view holders
- Local data persistence
- HTTP networking
- Material design implementation
- Error handling and validation
