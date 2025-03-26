# Build stage
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

# Set working directory
WORKDIR /app

# Copy pom files
COPY pom.xml .
COPY standard-library/pom.xml standard-library/
COPY service-example/pom.xml service-example/

# Copy source code
COPY standard-library/src standard-library/src
COPY service-example/src service-example/src

# Build the application
RUN mvn clean install -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built artifact from builder stage
COPY --from=builder /app/service-example/target/service-example-1.0.0.jar app.jar

# Expose port
EXPOSE 8080

# Set entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]
