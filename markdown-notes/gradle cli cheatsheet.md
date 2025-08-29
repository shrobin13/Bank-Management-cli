## 🔧 Project Initialization

```bash
gradle init                   # Initialize a new Gradle project
gradle init --type java-application   # New Java app project
gradle init --type java-library       # New Java library project
gradle init --dsl groovy              # Use Groovy DSL (default)
gradle init --dsl kotlin              # Use Kotlin DSL
```

---

## ⚡ Common Tasks

```bash
gradle tasks         # List available tasks
gradle tasks --all   # List all tasks (detailed)
gradle help          # Show help
gradle -v            # Show Gradle version
```

---

## 📦 Build & Lifecycle

```bash
gradle build         # Compiles, tests, and assembles the project
gradle assemble      # Just assemble (no tests)
gradle check         # Run checks (tests, lint, etc.)
gradle clean         # Clean build outputs
```

---

## 🛠️ Compilation

```bash
gradle compileJava   # Compile main source
gradle compileTestJava # Compile tests
gradle classes       # Compile + process resources
```

---

## ▶️ Running & Testing

```bash
gradle run           # Run main class (if application plugin is applied)
gradle test          # Run unit tests
gradle test --info   # Run tests with more details
gradle test --tests com.example.MyTest  # Run a specific test
```

---

## 📚 Dependencies

```bash
gradle dependencies            # Show dependency tree
gradle dependencies --configuration compileClasspath  # Inspect classpath
gradle buildEnvironment        # Show buildscript dependencies
```

---

## ⚡ Performance

```bash
gradle build --scan     # Generate build scan (performance report)
gradle build --parallel # Parallel execution
gradle build --daemon   # Use Gradle daemon (faster builds)
```

---

## 🧩 Wrapper (Recommended)

```bash
gradle wrapper                       # Generate wrapper scripts
./gradlew build                      # Use wrapper for builds
./gradlew tasks                      # Wrapper with tasks
./gradlew clean build test           # Wrapper full cycle
```

---

## 🌍 Useful Options

```bash
gradle build -x test      # Skip tests
gradle build --offline    # Use cached dependencies only
gradle build --refresh-dependencies  # Force refresh deps
gradle build --stacktrace # Show full error stacktrace
gradle build --info       # Detailed logs
gradle build --debug      # Debug-level logs
```

---

## 📁 Project Structure (Java Quickstart)

```
project-root/
 ├── build.gradle   # Build script (Groovy) or build.gradle.kts (Kotlin)
 ├── settings.gradle
 └── src
     ├── main
     │   ├── java
     │   └── resources
     └── test
         ├── java
         └── resources
```

---
