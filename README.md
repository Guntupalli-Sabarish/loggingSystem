# Distributed Logging System

## Overview
A high-performance, asynchronous logging system designed to capture, process, and visualize application logs in real-time. It features a thread-safe Java backend that decouples log ingestion from disk I/O, ensuring zero impact on main application performance, coupled with a responsive React dashboard for monitoring.

## Key Features
*   **Asynchronous Processing**: Logs are processed in the background, preventing main thread blocking.
*   **Real-Time Dashboard**: Live monitoring of system events with auto-refresh.
*   **Advanced Filtering**: Filter logs by "Search Term" (supporting Regex) and "Severity Level" (INFO, WARN, ERROR).
*   **Persistent Storage**: Automatic archiving of logs to a local file system (`system.log`).
*   **Simulated Traffic**: Built-in tools to simulate high-concurrency log events for testing.

## How It Works (Architecture)

### 1. The Core Engine (`Logger.java`)
The backend is built on the **Producer-Consumer Design Pattern** to maximize throughput:
*   **The Producer (Fast)**: When an application event occurs (`addLog`), the log data is instantly pushed into a thread-safe `LinkedBlockingQueue`. This operation is non-blocking, allowing the application to continue execution immediately.
*   **The Consumer (Reliable)**: A dedicated background daemon thread continuously monitors the queue. It retrieves logs one by one and writes them to the persistent storage (`FileStore`).
*   **Memory Management**: A separate `CopyOnWriteArraySet` maintains a recent history of logs in memory, facilitating fast read access for the frontend dashboard without hitting the disk.

### 2. The Singleton Service
The system enforces a **Singleton Pattern** for the Logger service. This ensures that a single, globally separate instance manages all thread synchronization, preventing race conditions or file locking issues when multiple parts of the application try to log simultaneously.

### 3. Frontend-Backend Communication
*   The React frontend polls the Spring Boot API every 2 seconds to fetch the latest state.
*   Data is filtered client-side for immediate feedback during search operations.

## Technology Stack
*   **Backend**: Java 17, Spring Boot 3.x
*   **Frontend**: React.js, Vite
*   **Storage**: Local File System
*   **Build Tools**: Maven, npm

## How to Run Locally

### Prerequisites
*   Java 17 or higher
*   Node.js (v18+)

### Step 1: Start the Backend
1.  Open a terminal in the root directory.
2.  Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

### Step 2: Start the Frontend
1.  Open a *new* terminal window.
2.  Navigate to the frontend directory:
    ```bash
    cd frontend
    ```
3.  Install dependencies (first time only):
    ```bash
    npm install
    ```
4.  Run the development server:
    ```bash
    npm run dev
    ```
    The application will open at `http://localhost:5173`.

## API Endpoints
*   `GET /api/logs`: Retrieve all current logs from memory.
*   `POST /api/logs`: Submit a new log entry.
    *   Body: `{ "data": "Error message", "severity": "HIGH", "threadName": "main" }`
*   `DELETE /api/logs`: Clear the in-memory log buffer.
