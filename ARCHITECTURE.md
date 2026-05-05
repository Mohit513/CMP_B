# Clean Architecture with Koin DI - Project Structure

## Overview
This project follows Clean Architecture principles with proper separation of concerns and Koin dependency injection.

## Project Structure

```
composeApp/src/commonMain/kotlin/com/example/cmp_b/
├── App.kt                           # Main application entry point
├── core/                            # Core infrastructure
│   ├── network/                      # Networking layer
│   │   ├── ApiService.kt            # API service interface and implementation
│   │   ├── HttpClientFactory.kt     # Ktor HTTP client configuration
│   │   ├── model/                   # Network DTOs
│   │   │   └── PostDto.kt
│   │   └── interceptors/            # HTTP interceptors
│   │       └── AuthInterceptor.kt
│   └── utils/                       # Core utilities
│       └── NetworkResult.kt         # Network result wrapper
├── shared/                          # Shared business logic
│   ├── domain/                      # Domain layer (pure Kotlin)
│   │   ├── model/                   # Domain models
│   │   │   └── Post.kt
│   │   ├── repository/              # Repository interfaces
│   │   │   └── PostRepository.kt
│   │   └── usecase/                 # Use cases
│   │       └── GetPostsUseCase.kt
│   ├── data/                        # Data layer implementations
│   │   ├── mapper/                  # Data mappers
│   │   │   └── PostMapper.kt
│   │   └── repository/              # Repository implementations
│   │       └── PostRepositoryImpl.kt
│   ├── presentation/                # Presentation layer
│   │   ├── viewmodel/               # ViewModels
│   │   │   └── PostListViewModel.kt
│   │   ├── state/                   # UI State
│   │   │   └── PostListState.kt
│   │   └── intent/                  # User intents
│   │       └── PostListIntent.kt
│   └── platform/                    # Platform-specific implementations
│       └── Platform.kt
├── feature/                         # Feature-specific modules
│   └── home/                        # Home feature
│       ├── domain/                  # Feature domain
│       │   ├── model/               # Feature models
│       │   │   └── HomePost.kt
│       │   └── usecase/            # Feature use cases
│       │       └── GetHomePostsUseCase.kt
│       ├── data/                    # Feature data layer
│       │   └── repository/         # Feature repositories
│       │       └── HomePostRepository.kt
│       └── presentation/            # Feature presentation
│           ├── screen/              # Feature screens
│           │   └── HomeScreen.kt
│           ├── viewmodel/           # Feature ViewModels
│           │   └── HomeViewModel.kt
│           ├── state/               # Feature states
│           │   └── HomeScreenState.kt
│           └── intent/              # Feature intents
│               └── HomeScreenIntent.kt
└── di/                             # Dependency Injection
    ├── AppModule.kt                 # Main app module
    ├── CoreModule.kt                # Core dependencies
    ├── SharedModule.kt              # Shared layer dependencies
    └── HomeModule.kt                # Home feature dependencies
```

## Architecture Layers

### 1. Core Layer
- **Network**: Ktor-based HTTP client setup
- **Utils**: Common utilities like NetworkResult wrapper

### 2. Shared Layer
- **Domain**: Pure Kotlin business logic with models, repositories interfaces, and use cases
- **Data**: Repository implementations with data mappers
- **Presentation**: Shared ViewModels, states, and intents

### 3. Feature Layer
- **Home**: Complete feature implementation with its own domain, data, and presentation layers
- Each feature is self-contained and can be developed independently

### 4. Dependency Injection
- **Koin**: Modern Kotlin dependency injection framework
- Modular setup with separate modules for each layer

## Key Principles

1. **Separation of Concerns**: Each layer has a specific responsibility
2. **Dependency Inversion**: High-level modules don't depend on low-level modules
3. **Single Responsibility**: Each class has one reason to change
4. **Feature Modularity**: Features are isolated and reusable
5. **Platform Abstraction**: Platform-specific code is properly separated

## Technology Stack

- **KMP**: Kotlin Multiplatform for cross-platform development
- **Ktor**: HTTP client for networking
- **Koin**: Dependency injection
- **Compose**: Modern UI framework
- **Coroutines & Flow**: Asynchronous programming
- **MVVM**: Model-View-ViewModel architecture pattern

## Benefits

- **Testability**: Each layer can be unit tested independently
- **Maintainability**: Clear separation makes code easier to maintain
- **Scalability**: New features can be added without affecting existing code
- **Reusability**: Shared components can be reused across features
- **Platform Independence**: Business logic is platform-agnostic
