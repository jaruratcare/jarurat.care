# Use a lightweight OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/hopebot-1.0.0.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
