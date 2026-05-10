# 🛒 POS System Backend API

A scalable and secure Point of Sale (POS) backend system developed using Spring Boot.  
This project is designed to manage products, inventory, branches, authentication, and order processing with transactional business logic and JWT-based security.

---

# 📖 Overview

The POS System Backend provides REST APIs for handling core retail operations such as:

- Product Management
- Inventory Tracking
- Branch Management
- User Authentication
- Order Processing

The application follows layered architecture and industry-standard backend development practices using Spring Boot and Hibernate.

---

# 🚀 Features

## 🔐 Authentication & Authorization
- JWT Authentication
- Secure Login & Registration APIs
- Spring Security Integration
- Role-based access structure

---

## 📦 Product Management
- Create and manage products
- Barcode and SKU support
- Product categorization
- Product activation/deactivation

---

## 🏬 Branch Management
- Multi-branch architecture
- Branch-specific inventory support

---

## 📊 Inventory Management
- Inventory quantity tracking
- Automatic stock reduction during order placement
- Inventory validation before order creation

---

## 🧾 Order Management
- Create customer orders
- Multiple order items support
- Dynamic subtotal and total calculations
- Transactional order processing
- Order retrieval APIs

---

# 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database Access |
| Hibernate | ORM Framework |
| MySQL | Relational Database |
| Maven | Dependency Management |
| Lombok | Boilerplate Reduction |
| JWT | Token-based Security |

---

# 🧱 Architecture

The project follows a layered architecture:

```text
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database
```

---

# 📂 Project Structure

```text
src/main/java/com/app
│
├── controller        # REST Controllers
├── dto               # Request & Response DTOs
├── entity            # JPA Entities
├── repository        # Database Repositories
├── service           # Service Interfaces
├── service/impl      # Business Logic Implementations
├── config            # Security & App Configuration
├── exception         # Custom Exceptions
└── security          # JWT & Authentication Logic
```

---

# ⚙️ Implemented APIs

## 🔑 Authentication APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register new user |
| POST | `/auth/login` | Authenticate user |

---

## 📦 Product APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/products` | Create product |

---

## 📊 Inventory APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/inventory` | Add inventory stock |

---

## 🧾 Order APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/orders` | Create order |
| GET | `/orders/{id}` | Get order details |

---

# 🧾 Sample Order Request

```json
{
  "branchId": 1,
  "items": [
    {
      "productId": 4,
      "quantity": 2
    }
  ]
}
```

---

# ✅ Sample Order Response

```json
{
  "orderId": 3,
  "status": "CREATED",
  "totalAmount": 150000.00,
  "items": [
    {
      "productName": "Laptop",
      "quantity": 2,
      "price": 75000.00,
      "subtotal": 150000.00
    }
  ]
}
```

---

# 🔥 Core Business Logic

The project implements several real-world backend concepts:

- Inventory validation before order creation
- Automatic stock deduction
- Transaction management using `@Transactional`
- DTO-based API communication
- Exception handling with custom exceptions
- Secure authenticated order creation

---

# ❌ Custom Exceptions

- `ProductNotFoundException`
- `BranchNotFoundException`
- `InvalidQuantityException`
- `InsufficientInventoryException`

---

# 🗄️ Database

The project uses MySQL as the primary relational database.

Main entities include:

- User
- Product
- Category
- Branch
- Inventory
- Order
- OrderItem

---

# ▶️ Getting Started

## 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/your-repository-name.git
```

---

## 2️⃣ Configure Database

Update your `application.properties`

```properties
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 3️⃣ Run Application

```bash
mvn spring-boot:run
```

Application runs on:

```text
http://localhost:4000
```

---

# 📌 Future Enhancements

- Order status updates
- Payment integration
- Swagger/OpenAPI documentation
- Pagination & filtering
- Sales analytics
- Docker support
- Unit & Integration testing
- Redis caching

---

# 🧠 Learning Outcomes

This project helped in understanding:

- Spring Boot application architecture
- REST API development
- JWT Authentication
- Transaction management
- Hibernate entity relationships
- Inventory and order business logic
- Exception handling best practices

---

# 👨‍💻 Author

Developed as a backend portfolio project to practice enterprise-level Spring Boot application development and real-world API design.
