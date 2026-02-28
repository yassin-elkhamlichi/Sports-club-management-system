# Sports Club Management System [![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.3-brightgreen.svg)](https://spring.io/projects/spring-boot) [![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

## 📖 Overview
The Sports Club Management System is a robust backend application designed to streamline the operations of sports clubs. It aims to digitize and manage the core activities of a sports facility, including members, coaches, teams, matches, attendances, players, subscriptions, and ticketing. By providing a comprehensive suite of REST APIs, this system acts as the centralized platform for all management needs, enhancing operational efficiency and providing clear insight into the club's activities.

This application is built with modern software architecture principles, employing a multi-layered structure to ensure scalability, ease of maintenance, and separation of concerns.

## 🛠️ Tech Stack & Expert Technologies

This project uses a modern Java stack centered around the Spring ecosystem:

*   **Core Framework:** `Java 21` & `Spring Boot 3.4.3`
*   **Database & ORM:** `MySQL 8` & `Spring Data JPA` (Hibernate)
*   **Security:** `Spring Security` & `JWT (JSON Web Tokens)` for stateless, secure authentication.
*   **API Documentation:** `SpringDoc OpenAPI` (Swagger UI) for interactive API documentation.
*   **Database Migration:** `Flyway` for version-controlled database schema changes, ensuring consistency across environments.
*   **Mapping:** `MapStruct` for efficient, type-safe mapping between Entities and Data Transfer Objects (DTOs).
*   **Boilerplate Reduction:** `Lombok` to generate getters, setters, constructors, and builders automatically.
*   **Testing:** `JUnit 5`, `Mockito`, and `Spring Boot Test` for unit testing the layers.
*   **Containerization:** `Docker` & `Docker Compose` for easy deployment and environment consistency.
*   **Environment Management:** `java-dotenv` for loading environment variables securely from `.env` files.

## 🧩 Architecture & Key Components

The system is designed following the standard Controller-Service-Repository pattern:

1.  **Security Layer :** Handles authentication using JWT tokens and secures endpoints based on user roles (`Admin`, `Member`, `Coach`, etc.).
2.  **Controller Layer :** Defines the RESTful API endpoints. Handles incoming HTTP requests and routes them to the appropriate services. Includes a `GlobalExceptionHandler` for consistent error handling.
3.  **Service Layer :** Contains the core business logic.
4.  **Repository Layer :** Interfaces extending `JpaRepository` for data access operations.
5.  **Entity Layer :** The JPA domain model mapping directly to database tables (e.g., `User`, `Member`, `Coach`, `Team`, `Match`, `Ticket`, `Subscription`, `Attendance`).
6.  **DTO & Mapper Layer :** Data Transfer Objects ensure decoupling of the database schema from the API response payload. `MapStruct` handles the translation between these layers seamlessly.
7.  **Database Migrations :** SQL scripts defining the exact schema changes (V1 to V7), creating tables, inserting initial data, and altering schema safely over time.

## ✨ Key Functionalities

*   **Authentication & Authorization:** Secure login system using JWT. Different authorization levels for Admins compared to regular users.
*   **User Management:** Registering and managing different types of entities like `Members`, `Players`, `Coaches`, and `Admins`.
*   **Team & Match Operations:** Organizing `Players` into `Teams`, scheduling `Matches`, and managing facilities.
*   **Attendance Tracking:** Logging attendances for specific instances.
*   **Financial & Ticketing:** Managing `Subscriptions` (and `SubscriptionPlans`) for members, and handling the purchasing of `Tickets` for specific matches. Includes features like automated QR code generation logic integrated into database schemas.

## 🏆 Why This Architecture is Good (Expert View)

1.  **Separation of Concerns (SoC):** The strict division between Controllers, Services, and Repositories ensures that changes in one layer (e.g., swapping out the database) have minimal impact on others (like the API definitions).
2.  **Security Best Practices:** Utilizing stateless JWT tokens provides high security suitable for microservices. Storing secrets in Docker Secrets instead of hardcoding or plaintext `.env` files within production demonstrates production-readiness.
3.  **Predictable Database State:** The use of `Flyway` guarantees that the database schema is identical across development, staging, and production environments, eliminating "it works on my machine" errors related to database structure.
4.  **Maintainability:** `MapStruct` significantly reduces manual mapping errors and boilerplate. `Lombok` keeps entity and DTO classes incredibly clean.
5.  **Robust Exception Handling:** A `@ControllerAdvice` (`GlobalExceptionHandler`) ensures that arbitrary Java exceptions are caught and translated into standardized JSON error responses with appropriate HTTP status codes.
6.  **Containerized Environment:** The inclusion of `Dockerfile` and `docker-compose.yml` makes onboarding new developers and deploying to servers a one-command process (`docker-compose up`).

## 🚀 How to Run and Test

### Prerequisites
*   [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/)
*   Java Development Kit (JDK) 21 (if running locally without Docker)
*   Maven

### 1. Using Docker Compose (Recommended)

This is the easiest way to get the system running, as it sets up both the MySQL database and the Spring Boot application.

1.  **Configure Secrets:** Ensure you have the `secrets/` directory created at the root with the required files (`db_password.txt`, `db_root_password.txt`, `jwt_secret.txt`). Add your desired secure passwords/keys inside these files.
2.  **Configure Environment:** You need a `.env` file at the root. You can copy the template:
    ```bash
    cp .env.example .env
    ```
    Populate `.env` with variables like `DB_USERNAME`, `JWT_ACCESS_EXPIRATION`, and `JWT_REFRESH_EXPIRATION`.
3.  **Build and Start:**
    ```bash
    docker-compose up -d --build
    ```
    This will pull the MySQL image, build the Spring app image, execute Flyway migrations, and start the services.
4.  **Stop:**
    ```bash
    docker-compose down
    ```

### 2. Running Locally (Development Mode)

1.  Ensure you have a local MySQL instance running or use Docker to run *only* the database: `docker-compose up -d mysql`
2.  Ensure your environment variables (from `.env` or system vars) are set so Spring can connect to the DB.
3.  Run the application using Maven wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```

### 3. Testing the Application

*   **Unit & Integration Tests:**
    The project uses JUnit and Mockito. You can run the full test suite using:
    ```bash
    ./mvnw test
    ```
*   **API Testing (Swagger UI):**
    Once the application is running, you can interactively explore and test the API endpoints using the Swagger UI:
    Navigate to `http://localhost:8090/swagger-ui.html` (or `8080` if running locally outside docker). You will need to authenticate first, grab the JWT token, and click the "Authorize" button in Swagger to test secured endpoints.
