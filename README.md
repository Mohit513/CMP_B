# KMP Advance Multiplatform Architecture

This project follows a professional-grade architecture designed for high-performance Android and iOS applications using Compose Multiplatform (CMP).

## 🚀 Key Features
- **Clean Architecture**: Separation of concerns (Data, DI, UI, Util).
- **Networking**: Ktor 3.0 with JSON serialization and robust error handling.
- **Dependency Injection**: Koin 4.0 for seamless multiplatform DI.
- **Network Inspection**: 
  - **Android**: [Chucker](https://github.com/ChuckerTeam/chucker) integrated for in-app HTTP inspection.
  - **iOS**: Ktor Logging configured for Xcode console debugging.
- **State Management**: Sealed `NetworkResult` class for Loading/Success/Error handling.

## 🖼️ Using Images (SVG & PNG)
Compose Multiplatform uses **Compose Resources** instead of the traditional Android `R.drawable`.

### 1. Place your files
Add your images to:
`composeApp/src/commonMain/composeResources/drawable/`

- **SVG**: Keep them as `.svg` (No need to convert to Vector Drawable).
- **PNG/JPG**: standard image formats are supported.

### 2. Usage in Code
After adding a file (e.g., `logo.svg`), build the project. Then use:

```kotlin
import org.jetbrains.compose.resources.painterResource
import com.example.cmp_b.generated.resources.Res
import com.example.cmp_b.generated.resources.logo

Image(
    painter = painterResource(Res.drawable.logo),
    contentDescription = "Logo"
)
```

> **Note**: If you see errors on `Res`, run **./gradlew generateComposeResClass** or simply **Build -> Rebuild Project** in Android Studio to generate the resource accessors.

## 📁 Project Structure
- `commonMain`: Shared logic, repositories, and UI.
- `androidMain`: Android-specific implementations (Chucker, Koin context).
- `iosMain`: iOS-specific implementations (Darwin engine, Xcode logging).

## 🛠 Setup & Run
1. Sync Gradle files to download dependencies.
2. **Android**: Run the `composeApp` module.
3. **iOS**: 
   - Open `iosApp/iosApp.xcworkspace` in Xcode.
   - Run `initKoinIos()` in your AppDelegate.
   - Deploy to a physical device or simulator.

## 📦 Tech Stack
- **UI**: Compose Multiplatform
- **DI**: Koin
- **Network**: Ktor
- **Serialization**: Kotlinx Serialization
- **Inspection**: Chucker (Android)
