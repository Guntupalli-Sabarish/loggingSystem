# 🚀 Distributed Logging System Project

## 1. SITUATION: Why was this project needed?
In modern software environments, tracking application behavior in real-time is critical. We needed a centralized, non-blocking system to capture, store, and visualize logs from various parts of an application. The goal was to create a robust monitoring tool that could handle high concurrency without slowing down the core application logic.

## 2. TASK: Algorithms, Patterns, and Code Used
To solve this, we employed several key software engineering principles and design patterns:
- **Design Patterns:**
  - **Singleton Pattern:** Used for the `Logger` service to ensure a single, globally accessible instance coordinates all logging activities.
  - **Producer-Consumer Pattern:** Implemented using a Queue to handle log ingestion asynchronously (decoupling log creation from writing, though simplified for this demo version).
- **Data Structures:** 
  - `CopyOnWriteArraySet`: Used for thread-safe storage of logs in memory to handle concurrent read/write operations.
- **Algorithms:**
  - **Filtering Algorithms:** Custom linear search logic in the frontend to filter logs by *Search Term* and *Severity Level* (O(n) complexity).

## 3. IN ACTION: Development, Challenges & Solutions
**My Involvement:**
I designed and built the full-stack architecture, connecting a Java Spring Boot backend with a reactive React.js frontend.

**Technologies Used:**
- **Backend:** Java 17, Spring Boot 3.2, Maven, REST APIs.
- **Frontend:** React.js, Vite, Vanilla CSS (Glassmorphism UI).
- **Deployment/Tools:** Docker, Git, VS Code.

**Difficulties Faced & Overcoming Them:**
1.  **CORS Errors (Cross-Origin Resource Sharing):** 
    - *Problem:* The React frontend (port 5173) was blocked from calling the Java backend (port 8080).
    - *Solution:* Implemented a Global CORS Configuration bean in Spring Boot to map `/**` and allow all origins and methods (GET, POST, DELETE).
2.  **Deployment Port Mismatches:**
    - *Problem:* Cloud platforms like Render assign dynamic ports, but the app was hardcoded to 8080.
    - *Solution:* Updated `application.properties` to bind to `${PORT:8080}` and configured the `Dockerfile` to respect environment variables.
3.  **Data Persistence:**
    - *Problem:* Logs were lost on server restart.
    - *Solution:* Implemented a `FileStore` class to append logs to a local `system.log` file, ensuring historical data is saved.

## 4. RESULT: Final Outcome & Deployment
**The Result:**
We successfully built a **"System Monitor"** dashboard that allows users to:
- **Simulate Logs** with custom severity (High, Warn, Low).
- **Visualise Data** in a clean, modern dark-mode UI.
- **Filter & Search** logs instantly.
- **Clear Logs** on demand to declutter the workspace.

**Accuracy:**
The system provides **real-time** updates (polling every 2 seconds) with **100% data consistency** between the backend memory and the frontend display during local stress tests.

**Deployment:**
- **Current Status:** Optimized for **Local Development**.
- **Backend:** Runs on `localhost:8080` (Spring Boot).
- **Frontend:** Runs on `localhost:5173` (Vite).
- **Cloud Readiness:** The project contains a `deployment-setup` branch with full Docker and Vercel/Render configurations ready for live deployment.

