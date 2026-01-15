# ----------------------------------
# Stage 1: Build the JAR (Compiler)
# ----------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy generic Pom.xml
COPY pom.xml .
# Copy source code
COPY src ./src

# Build it (skip tests to be faster)
RUN mvn clean package -DskipTests

# ----------------------------------
# Stage 2: Run the JAR (Runtime)
# ----------------------------------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy from Stage 1 (the built jar)
# Note: We use wildcards (*) to handle slight version name changes
COPY --from=build /app/target/*.jar app.jar

# Run
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
