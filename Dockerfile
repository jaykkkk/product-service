# Java 25 image
FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

# Copy Maven wrapper & pom for caching dependencies
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Expose port
EXPOSE 8080

# Run jar
CMD ["java", "-jar", "target/product-service-0.0.1-SNAPSHOT.jar"]
