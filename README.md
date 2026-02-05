# Sports Club Management System (REST API)

**One-liner:** A robust, production-ready RESTful API designed to manage the lifecycle of club members, coaches, and subscription plans with a focus on data integrity and scalable architecture.

---

## 🚀 Features

* **Member Management:** Full CRUD operations for tracking member profiles and enrollments.


* **Coach & Plan Scheduling:** Manage coaching staff and various subscription tiers/plans.


* **Strict Data Validation:** Ensuring zero-fault data entry using Jakarta Validation.


* **Centralized Error Handling:** Global exception management for consistent API responses.



## 🛠 Tech Stack

* **Backend:** Java 17, Spring Boot 3 


* **Data Access:** Spring Data JPA, Hibernate 


* **Database:** MySQL 


* **Validation:** Jakarta Validation (Bean Validation) 


* **API Documentation:** OpenAPI / Swagger 


* **Tools:** Postman, Maven 



## 🏗 Architecture & Design Patterns

To align with **Software Quality** standards, this project implements:

* **DTO Pattern:** Strictly separates the internal domain model from client-side views to ensure data encapsulation and security.


* **Domain-Driven Design (DDD) Principles:** Modular package structure for better maintainability.


* **JPA Relationship Mapping:** Complex data modeling including One-to-Many and Many-to-Many relationships to track activity schedules.


* **AOP-based Exception Handling:** Utilizes `@ControllerAdvice` to decouple error handling from business logic.



## 🔧 Installation

1. **Clone the repository:**
```bash
git clone https://github.com/yassin-elkhamlichi/Sports-club-management-system.git

```


2. **Configure Database:** Update `src/main/resources/application.properties` with your MySQL credentials.
3. **Build the project:**
```bash
mvn clean install

```


4. **Run:**
```bash
mvn spring-boot:run

```

