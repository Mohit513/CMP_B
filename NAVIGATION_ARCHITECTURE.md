# Navigation Architecture - Clean & Scalable Setup

## Overview
This project implements a clean, scalable navigation system that's easy to understand and maintain. The navigation setup follows the principle of one-to-one screen switching with proper separation of concerns.

## Navigation Structure

```
core/navigation/
├── NavigationDestination.kt     # Base interface for all screens
├── NavigationManager.kt          # State management and navigation logic
├── NavigationHost.kt             # Screen rendering host
├── NavigationComposer.kt         # Main navigation composer with bottom nav
├── BottomNavigationBar.kt        # Bottom navigation component
└── destinations/                 # All screen destinations
    ├── HomeDestination.kt
    ├── ProfileDestination.kt
    ├── SettingsDestination.kt
    └── PostDetailDestination.kt
```

## Key Components

### 1. NavigationDestination Interface
```kotlin
interface NavigationDestination {
    val route: String                    // Unique route identifier
    val title: String                    // Display title
    val iconRes: String?                 // Icon for bottom nav (optional)
    val showInBottomNav: Boolean         // Show in bottom navigation
    fun Content(modifier: Modifier)      // Screen content
}
```

### 2. NavigationManager
- **State Management**: Manages current destination and navigation stack
- **Navigation Logic**: Handles forward/back navigation
- **Stack Management**: Maintains navigation history for back button

### 3. NavigationController
- **Convenient API**: Easy-to-use navigation methods
- **Route Resolution**: Navigate by route string
- **Bottom Nav Integration**: Filter destinations for bottom navigation

### 4. NavigationComposer
- **Main Container**: Combines navigation host with bottom navigation
- **Auto-initialization**: Sets up default destination on app start
- **Conditional UI**: Shows/hides bottom navigation based on destination

## Screen Destinations

### Home Destination
```kotlin
object HomeDestination : BaseNavigationDestination() {
    override val route: String = "home"
    override val title: String = "Home"
    override val showInBottomNav: Boolean = true
}
```

### Parameterized Destinations
```kotlin
object PostDetailDestination : BaseNavigationDestination() {
    override val route: String = "post_detail"
    override val routeWithArgs: String = "post_detail/{postId}"
    
    fun createRoute(postId: Int): String {
        return createRoute("postId" to postId)
    }
}
```

## Navigation Usage

### Basic Navigation
```kotlin
// Navigate to a destination
navigationController.navigate(ProfileDestination)

// Navigate by route
navigationController.navigate("profile")

// Navigate with parameters
navigationController.navigate(PostDetailDestination.createRoute(postId))
```

### Back Navigation
```kotlin
// Navigate back
navigationController.navigateBack()

// Check if can go back
if (navigationController.canNavigateBack()) {
    navigationController.navigateBack()
}
```

### Feature-based Navigation
```kotlin
// In HomeScreen, navigate to post detail
onPostClick = { 
    navigationController.navigate(PostDetailDestination.createRoute(post.id))
}
```

## Dependency Injection Setup

### Navigation Module
```kotlin
val navigationModule = module {
    single<List<NavigationDestination>> {
        listOf(
            HomeDestination,
            ProfileDestination,
            SettingsDestination,
            PostDetailDestination
        )
    }
    single { NavigationManager() }
    single { NavController(get(), get()) }
}
```

## Benefits of This Architecture

### 1. **Clean Separation**
- Each screen is a self-contained destination
- Navigation logic is separated from UI logic
- Easy to test and maintain

### 2. **Scalability**
- Adding new screens is as simple as creating a new destination
- No need to modify existing navigation logic
- Supports complex navigation flows

### 3. **Type Safety**
- Compile-time checking for navigation routes
- Parameter validation for destinations with arguments
- IDE support for navigation methods

### 4. **Flexibility**
- Support for both bottom navigation and modal screens
- Easy to implement deep linking
- Custom navigation patterns supported

### 5. **State Management**
- Automatic navigation stack management
- State preservation across configuration changes
- Back button handling

## Adding New Screens

### Step 1: Create Destination
```kotlin
object NewScreenDestination : BaseNavigationDestination() {
    override val route: String = "new_screen"
    override val title: String = "New Screen"
    override val showInBottomNav: Boolean = false
    
    @Composable
    override fun Content(modifier: Modifier) {
        NewScreen(modifier = modifier)
    }
}
```

### Step 2: Add to Navigation Module
```kotlin
single<List<NavigationDestination>> {
    listOf(
        // ... existing destinations
        NewScreenDestination  // Add new destination
    )
}
```

### Step 3: Navigate to Screen
```kotlin
navigationController.navigate(NewScreenDestination)
```

## Advanced Features

### Deep Linking
```kotlin
// Handle deep links
fun handleDeepLink(deepLink: String) {
    when {
        deepLink.startsWith("app://post/") -> {
            val postId = deepLink.substringAfterLast("/")
            navigationController.navigate(PostDetailDestination.createRoute(postId.toInt()))
        }
        // Handle other deep link patterns
    }
}
```

### Conditional Navigation
```kotlin
// Navigate based on user state
fun navigateBasedOnAuth(isLoggedIn: Boolean) {
    if (isLoggedIn) {
        navigationController.navigate(ProfileDestination)
    } else {
        navigationController.navigate(LoginDestination)
    }
}
```

### Navigation Guards
```kotlin
// Add navigation validation
fun navigateWithGuard(destination: NavigationDestination) {
    if (canNavigateTo(destination)) {
        navigationController.navigate(destination)
    }
}
```

## Best Practices

1. **One Destination Per Screen**: Each screen should have exactly one destination
2. **Clear Route Names**: Use descriptive, lowercase route names
3. **Parameter Validation**: Always validate navigation parameters
4. **Back Stack Management**: Consider the user journey when navigating
5. **Testing**: Test navigation flows and edge cases

## Migration from Other Navigation Libraries

This custom navigation system provides similar functionality to popular libraries but with:
- Better type safety
- Cleaner architecture
- Easier debugging
- More flexibility for custom requirements

The learning curve is minimal, and the benefits are significant for large-scale applications.
