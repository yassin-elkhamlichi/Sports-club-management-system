# ================================
# Stage 1 - BUILD
# ================================
FROM maven:3.9.6-eclipse-temurin-21 AS builder
LABEL authors="yassine"
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests -B

# ================================
# Stage 2 - RUN
# ================================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Debian/Ubuntu syntax
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

COPY --from=builder /app/target/*.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]