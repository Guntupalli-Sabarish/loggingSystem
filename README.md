# Logging System

# Full-Stack Logging System

## 1. Project Description
This project has been evolved into a **Full-Stack Web Service** for monitoring system logs. It consists of a high-performance Java Backend (Spring Boot) that buffers and processes logs, and a modern React Frontend used to visualize these logs in real-time.

**Key Features:**
- **Backend:** Spring Boot REST API for high-throughput log ingestion and retrieval.
- **Frontend:** React + Vite application with a modern "Cyberpunk/Glassmorphism" UI.
- **core:** The original efficient `Logger` Singleton pattern is now wrapped as a service.

## 2. Tech Stack
- **Backend:** Java 17, Spring Boot 3.2, Maven
- **Frontend:** React.js, Vite, Axios, Modern CSS
- **Testing:** JUnit 5

### Project Structure
- `src/main/java/logger`: The Spring Boot Backend.
- `frontend/`: The React Frontend source code.

## 3. How to Run

### Prerequisite
- Java 17+
- Node.js & npm

### Step 1: Start the Backend
Open a terminal in the root directory (`loggingSystem-1`) and run:
```bash
mvn spring-boot:run
```
The server will start on `http://localhost:8080`.

### Step 2: Start the Frontend
Open a **new** terminal, navigate to the frontend folder, and start the dev server:
```bash
cd frontend
npm run dev
```
Open your browser to the URL shown (usually `http://localhost:5173`).

## 4. Usage
- **View Logs:** The frontend automatically polls for new logs every 2 seconds.
- **Simulate Logs:** Use the input box in the UI to send a test log to the backend.

