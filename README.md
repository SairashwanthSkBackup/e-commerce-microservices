# 🛒 E-Commerce Microservices Backend

## 📌 Overview
This project is a *microservices-based e-commerce backend system* built using *Spring Boot. It demonstrates real-world backend architecture with **JWT-based authentication, **API Gateway routing, and **Dockerized deployment*.

The system consists of multiple independent services communicating over REST APIs and secured using token-based authentication.

---

## 🏗️ Architecture

Client (Postman / Frontend)
        ↓
API Gateway (Port 8080)
        ↓
-------------------------------------
| User Service     (Auth + JWT)     |
| Product Service  (Catalog)        |
| Order Service    (Orders)         |
-------------------------------------
        ↓
     PostgreSQL

---

## ⚙️ Tech Stack

- Backend: Java, Spring Boot
- Security: JWT (JSON Web Token)
- API Gateway: Spring Cloud Gateway
- Database: PostgreSQL
- Containerization: Docker, Docker Compose
- Build Tool: Maven
- Documentation: Swagger (OpenAPI)

---

## 🔐 Features

- User Registration & Login
- JWT Authentication & Authorization
- Secure Microservices Communication
- Product Management APIs
- Order Placement & Tracking
- API Gateway Routing
- Dockerized Deployment
- Automatic Database Initialization

---

## 🧩 Microservices

### 👤 User Service
- Register user
- Login user
- Generate JWT token
- Role-based structure (optional)

### 🛍️ Product Service
- Create product
- Get all products
- Get product by ID
- Secured using JWT

### 📦 Order Service
- Place order
- Fetch order details
- Calls Product Service internally
- Token propagation for secure communication

### 🌐 API Gateway
- Routes requests to respective services
- Single entry point (localhost:8080)
- Handles external traffic

---

## 🔄 API Flow

1. User logs in → receives JWT  
2. Client sends JWT in Authorization header  
3. Gateway routes request  
4. Services validate JWT  
5. Secure response returned  

---

## 📂 Project Structure

ecommerce-microservices/
│
├── user-service/
├── product-service/
├── order-service/
├── api-gateway/
├── docker-compose.yml
└── init.sql

---

## 🐳 Running the Project (Docker)

### 1️⃣ Build all services
mvn clean package -DskipTests

### 2️⃣ Start the system
docker-compose up --build

### 3️⃣ Access APIs
API Gateway: http://localhost:8080

---

## 📬 API Endpoints

### 🔐 Authentication
POST /auth/register  
POST /auth/login  

### 🛍️ Products
GET    /products  
GET    /products/{id}  
POST   /products  

### 📦 Orders
POST   /orders  
GET    /orders/{id}  

---

## 🔑 Sample Request (Login)

POST /auth/login

Request Body:
{
  "email": "user@example.com",
  "password": "password"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}

---

## 🔒 Authorization Header

Authorization: Bearer <token>

---

## 🧠 Key Concepts Implemented

- Microservices Architecture
- API Gateway Pattern
- JWT Authentication
- Inter-service Communication
- Token Propagation
- Docker Networking
- Database Initialization via Script

---

## ⚠️ Important Notes

- Use service names instead of localhost inside Docker
- Ensure databases are created via init.sql
- JWT secret must be same across services
- Skip tests during build if DB is not available

---

## 🚀 Future Enhancements

- Service Discovery (Eureka)
- Centralized Config Server
- Circuit Breaker (Resilience4j)
- Logging & Monitoring
- Frontend Integration (React)

---

## 💼 Interview Summary

This project demonstrates:
- Real-world microservices architecture
- Secure API design using JWT
- Containerized deployment using Docker
- Hands-on experience with distributed systems

---

## 👨‍💻 Author

Sairashwanth S K

---

## ⭐ If you found this useful, give it a star!