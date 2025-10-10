# Use Eclipse Temurin 17 JDK
FROM eclipse-temurin:17-jdk-alpine

# Install Maven
RUN apk add --no-cache maven bash

# Set working directory
WORKDIR /app

# Copy source code
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "target/hopebot-1.0.0.jar"]
