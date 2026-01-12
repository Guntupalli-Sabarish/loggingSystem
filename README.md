# Logging System

## 1. Project Description
This project is a custom Logging System framework implemented in Java. It is designed to manage and process logs efficiently, capable of handling log levels (severity), timestamps, thread information, and stack traces. The system utilizes a Singleton pattern for the primary Logger service and includes structures for buffering logs in memory before flushing them to a persistent file store.

**Note:** This project is currently under active development. Several core features (like file persistence logic and queue processing) are marked with TODOs and are being implemented.

## 2. Tech Stack
- **Language:** Java 17
- **Build Tool:** Maven
- **Testing Framework:** JUnit 5 (Jupiter)

### Project Structure
The source code is organized into the following packages:
- `logger.service`: Contains the core `Logger` class (Singleton) responsible for managing log aggregation and processing.
- `logger.pojo`: Defines the `Log` object model, including properties for data, timestamp, thread ID/name, severity, and stack trace.
- `logger.data`: Handles data persistence (e.g., `FileStore`).
- `logger.enums`: Contains enumerations such as `Severity`.

## 3. How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven installed and configured in your path

### Compilation
To compile the project and download necessary dependencies, run the following command in the project root directory:

```bash
mvn clean install
```

This will compile the source code and run any tests found in the `src/test` directory.

### Running the Application
The main entry point is `logger.App`. You can run the application using Maven or directly with Java after compilation.

**Using Maven:**
You can use the `exec-maven-plugin` (if configured) or simply compile and run via the classpath. Since the exec plugin isn't explicitly defined in the provided POM, the most reliable way to run from the command line without an IDE is:

1. **Build the project:**
   ```bash
   mvn clean package
   ```

2. **Run the Main Class:**
   Assuming the artifactId is somewhat standard (currently set to `.`), you might need to target the classes directory directly if a fat JAR isn't built.
   
   ```bash
   java -cp target/classes logger.App
   ```

**Using an IDE (IntelliJ IDEA, Eclipse, VS Code):**
1. Open the project as a Maven project.
2. Navigate to `src/main/java/logger/App.java`.
3. Right-click the file and select **Run 'App.main()'**.

## 4. Current Functionality
The `App.java` class currently demonstrates the Singleton behavior of the `Logger` class by printing the hash codes of two `getInstance()` calls.

```java
public class App {
    public static void main(String[] args) {
        System.out.println(Logger.getInstance());
        System.out.println(Logger.getInstance());
    }
}
```

Output should show the same object reference, confirming the Singleton pattern.
