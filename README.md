# Order API

A simple REST API for order management built with **Spring Boot**, **Spring Web MVC**, **Spring Data JPA**, and **MySQL**.

## 📌 Overview

This project implements an order service using a layered architecture:

- **Controller**: handles HTTP requests and exposes REST endpoints.
- **Service**: applies business rules.
- **Repository**: performs persistence and queries using JPA.
- **Entity**: represents the database table model.

Currently, the API provides:

- `POST /orders` to create an order.
- `GET /orders` to list all orders.

---

## 🧱 Tech Stack

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Web MVC**
- **Spring Data JPA**
- **MySQL Connector/J**
- **JUnit 5 + Mockito**
- **Gradle**

---

## 📂 Project Structure

```text
src/
├── main/
│   ├── java/com/leonardo/order_api/
│   │   ├── controller/
│   │   │   └── OrderController.java
│   │   ├── entity/
│   │   │   └── Order.java
│   │   ├── repository/
│   │   │   └── OrderRepository.java
│   │   ├── service/
│   │   │   └── OrderService.java
│   │   └── OrderApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/leonardo/order_api/
        ├── service/
        │   └── OrderServiceTest.java
        └── OrderApiApplicationTests.java
```

---

## 🗃️ Data Model

The `Order` entity is mapped to the `orders` table and contains:

- `id` (`Long`) — primary key with auto-increment.
- `customer` (`String`) — customer name.
- `product` (`String`) — purchased product.
- `price` (`Double`) — order amount.

---

## ⚙️ Configuration

File: `src/main/resources/application.properties`

Current settings:

- Database: `orderdb`
- URL: `jdbc:mysql://localhost:3306/orderdb`
- Username: `root`
- Password: `root`
- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.show-sql=true`

> Note: for production, update credentials and avoid exposing sensitive SQL logs.

---

## ▶️ Running Locally

### Prerequisites

- Java 21 installed
- MySQL running locally
- `orderdb` database created

### Steps

1. Clone the repository.
2. Update `application.properties` according to your environment.
3. Run the application:

```bash
./gradlew bootRun
```

By default, the API starts at `http://localhost:8080`.

---

## 🔌 Endpoints

### Create Order

- **Method:** `POST`
- **Route:** `/orders`
- **Body (JSON):**

```json
{
  "customer": "Leonardo",
  "product": "Notebook",
  "price": 4500.00
}
```

- **Business rule:** `price` must be greater than zero.

### List Orders

- **Method:** `GET`
- **Route:** `/orders`
- **Response:** list of orders.

---

## 🧠 Current Business Rule

In `OrderService`, when creating an order:

- If `price <= 0`, the application throws: `Price must be greater than zero`.
- Otherwise, the order is persisted.

---

## 🧪 Tests

The project includes unit tests for `OrderService` covering:

- successful creation;
- order listing;
- invalid price failure.

Run tests with:

```bash
./gradlew test
```

---

## 🚀 Suggested Improvements

To evolve the project:

1. Add update and delete endpoints (`PUT/PATCH`, `DELETE`).
2. Implement global error handling with `@ControllerAdvice`.
3. Add validation with `@Valid` + Bean Validation.
4. Introduce DTOs for input/output.
5. Add pagination and filtering for list operations.
6. Include API documentation with Swagger/OpenAPI.
7. Externalize configuration by profile (`dev`, `test`, `prod`).

---

## 👤 Author

Base project for Spring Boot study and practice.
