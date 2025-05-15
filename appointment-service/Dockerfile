# === Stage 1: Build ===
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# === Stage 2: Runtime ===
FROM openjdk:17-jdk-alpine

COPY --from=builder /app/target/appointment-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app.jar"]
