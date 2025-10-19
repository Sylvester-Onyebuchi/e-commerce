A robust E-Commerce backend built with Spring Boot, JWT authentication, Spring Security, AOP, Interceptors, Actuator, and fully Dockerized for easy deployment. This project provides REST APIs for user management, product catalog, and cart operations, with security, logging, and monitoring integrated.

TECH STACK

  Backend: Java, Spring Boot

  Database: PostgreSQL (Dockerized)

  Security: Spring Security + JWT

  Advanced Features:

  AOP (Aspect-Oriented Programming) for logging and metrics

  Interceptors for request pre-processing

  Actuator for monitoring and health checks

  Containerization: Docker, Docker Compose

  Other Tools: Maven, JPA/Hibernate, Lombok

  
  
FEATURES

User Management
Registration and login with JWT
Role-based access control (USER, ADMIN)
Product Management
CRUD operations for products
Admin-only endpoints
Cart Management
Add, remove, and update items
View user-specific cart
Security
Stateless JWT authentication
Token stored in HTTP-only cookies
Logging & Monitoring
Request logging with AOP
Metrics and health endpoints via Spring Boot Actuator
Dockerized
Easy setup for backend + PostgreSQL using Docker Compose


DOCKER SETUP

git clone https://github.com/Sylvester-Onyebuchi/ecommerce.git

cd ecommerce

docker-compose up --build

