# Use a Java 17 JDK base
FROM eclipse-temurin:17-jdk-alpine

# Create app directory
WORKDIR /app

# Copy in the built fat JAR
COPY build/libs/*.jar app.jar

# Expose port your Spring Boot app listens on
EXPOSE 8080

# Run it
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
