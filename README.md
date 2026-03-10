# Microservices Communication using Spring Boot, Eureka and FeignClient

## Overview

This project demonstrates how multiple Spring Boot microservices communicate with each other using **Spring Cloud OpenFeign** and **Eureka Service Discovery**.

The system consists of three services:

* **User Service**
* **Product Service**
* **Order Service**

Order Service communicates with User Service and Product Service using **FeignClient**, while **Eureka Server** is used for service discovery.

---

# Architecture

Client → Order Service → FeignClient → Eureka Server → User Service / Product Service

Explanation:

1. Client calls **Order Service**
2. Order Service calls **User Service** or **Product Service** using **FeignClient**
3. Feign asks **Eureka Server** where the service is running
4. Eureka returns the service instance (host + port)
5. Feign sends the request to that service

---

# Services

## 1. Eureka Server

Purpose:
Service discovery server where all microservices register.

Port:

```
8761
```

Main Class:

```java
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

application.properties

```
spring.application.name=eureka-server
server.port=8761

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

Dashboard:

```
http://localhost:8761
```

---

# 2. User Service

Port:

```
8081
```

Purpose:

Manages user data.

Example API:

```
GET /users/{id}
POST /users
```

application.properties

```
spring.application.name=user-service
server.port=8081

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

# 3. Product Service

Port:

```
8082
```

Purpose:

Manages product data.

Controller Example:

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
```

application.properties

```
spring.application.name=product-service
server.port=8082

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

---

# 4. Order Service

Port:

```
8083
```

Purpose:

Handles orders and communicates with other services.

Example:

```
GET /orders/{id}
```

Order Service calls:

* User Service
* Product Service

using **FeignClient**.

---

# Feign Client

FeignClient must be written in the **service which calls another service**.

Example:

Order Service → calls → User Service

So FeignClient is created inside **Order Service**.

Example:

```java
@FeignClient(name = "user-service")
public interface UserServiceFeign {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable Long id);

}
```

Product FeignClient:

```java
@FeignClient(name = "product-service")
public interface ProductServiceFeign {

    @GetMapping("/products/{id}")
    ProductDTO findProductById(@PathVariable Long id);

}
```

---

# Important Rules

### 1. FeignClient location

FeignClient must exist in the **calling service**.

Example:

```
Order Service → Feign → User Service
Order Service → Feign → Product Service
```

---

### 2. Service name must match

FeignClient:

```
@FeignClient(name="user-service")
```

Must match:

```
spring.application.name=user-service
```

---

### 3. DTO instead of Entity

Services should not share database entities.

Use DTO.

Example:

```
UserDTO
ProductDTO
```

---

### 4. Enable Feign

In Order Service main class:

```java
@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {
}
```

---

# Dependencies

Common dependencies:

```
Spring Web
Spring Data JPA
MySQL Driver
Lombok
```

Microservices dependencies:

```
spring-cloud-starter-openfeign
spring-cloud-starter-netflix-eureka-client
spring-cloud-starter-netflix-eureka-server
```

---

# Project Structure

Example structure for Order Service:

```
order-service
│
├── controller
│     OrderController
│
├── service
│     OrderService
│
├── repository
│     OrderRepository
│
├── entity
│     Orders
│
├── dto
│     UserDTO
│     ProductDTO
│
└── feign
      UserServiceFeign
      ProductServiceFeign
```

---

# Run Order

Always start services in this order:

```
1. Eureka Server
2. User Service
3. Product Service
4. Order Service
```

---

# Example Flow

Client calls:

```
GET /orders/1
```

Order Service:

```
find order
↓
call user-service using Feign
↓
call product-service using Feign
↓
combine response
↓
return to client
```

---

# Key Concepts Learned

* Microservices architecture
* Service discovery using Eureka
* Inter-service communication using FeignClient
* DTO usage in microservices
* Load balancing with service names
* Debugging Feign errors

---

# Future Improvements

* API Gateway
* Circuit Breaker (Resilience4j)
* Config Server
* Authentication Service (JWT)
* Dockerizing microservices

---
