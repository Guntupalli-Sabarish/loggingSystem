# 1. Use standard Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# 2. Working Directory
WORKDIR /app

# 3. Copy the bits
COPY target/logging-system-1.0-SNAPSHOT.jar app.jar

# 4. Run the app
# Use -Dserver.port=$PORT so Render can set the port dynamically
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
