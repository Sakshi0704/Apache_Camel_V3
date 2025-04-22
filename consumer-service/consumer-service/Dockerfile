# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all files to the container
COPY . .

# Build the project using Maven wrapper
RUN ./mvnw clean package -DskipTests

# Run the built jar
CMD ["java", "-jar", "target/consumer-service-0.0.1-SNAPSHOT.jar"]
