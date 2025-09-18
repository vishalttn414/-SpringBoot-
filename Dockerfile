# ---------- Stage 1: Build the JAR ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the JAR ----------
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copy only the final JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app uses
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]

