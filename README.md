# Skill Exchange App

Skill Exchange is a native Android app for connecting people who want to learn, teach, and exchange practical skills. Users can register, sign in, post learning needs, browse available requests, make offers, manage their profile, and view swap history.

## Current UI

The app has been updated with a refreshed visual style:

- Teal, coral, and warm neutral color scheme
- Redesigned splash, login, and dashboard screens
- Updated cards, chips, toolbar styling, and feed items
- Cleaner labels for request browsing and profile actions
- Lightweight XML-based UI for better emulator compatibility

## Features

- User registration and secure login
- Profile creation and editing
- Post skill-learning requests
- Browse recent skill requests
- Offer help on posted needs
- Track trust score and completed collaborations
- View swap/history records
- Settings screen for preferences

## Tech Stack

- Kotlin
- Android SDK
- XML layouts
- Material Components / Material 3
- Room database
- MVVM architecture
- Gradle Kotlin DSL

## Project Structure

```text
app/src/main/java/com/example/skillexchangeapp
|-- data
|   |-- local
|   |   |-- dao
|   |   `-- entity
|   `-- repository
|-- ui
|   |-- adapter
|   |-- fragment
|   |-- theme
|   `-- viewmodel
`-- utils
```

## How To Run

1. Clone the repository:

```bash
git clone https://github.com/shvetz45/Skill-Exchange-app.git
```

2. Open the project in Android Studio.

3. Wait for Gradle Sync to complete.

4. Start an Android emulator or connect an Android device.

5. Click Run in Android Studio.

You can also build from terminal:

```powershell
cd Skill-Exchange-app
.\gradlew.bat assembleDebug
```

To install on a connected emulator/device:

```powershell
.\gradlew.bat installDebug
```

## Emulator Recommendation

For smoother performance on low-storage or lower-RAM laptops, use a light emulator:

- Device: Medium Phone, Pixel 4, or Pixel 5
- API: 35 or 36
- RAM: 2048 MB
- Internal storage: 8 GB
- Prefer Google APIs x86_64 image

Avoid large devices like Pixel 9 Pro or API 37 images if the emulator is lagging.

## Repository

GitHub: https://github.com/shvetz45/Skill-Exchange-app

## Purpose

This app was built as an educational Android project to support peer learning, local skill discovery, and structured collaboration between learners and mentors.
