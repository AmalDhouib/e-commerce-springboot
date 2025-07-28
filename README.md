# E-commerce Backend – Spring Boot

This is the backend application for an e-commerce platform built using **Spring Boot**, connected to a **MySQL** database, and configured to send emails via **Gmail SMTP**.

## Features

- User authentication and registration
- Product management (CRUD)
- Order and cart system
- Favorites and discovery page support
- Email notifications via Gmail

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven
- Gmail SMTP

## Requirements

- Java 17+
- Maven
- MySQL 8+
- Git

## Configuration

Edit the file:  
`src/main/resources/application.properties`

```properties
spring.application.name=ecommerce
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

# Mail Configuration (Optional)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_GMAIL_ADDRESS
spring.mail.password=YOUR_GMAIL_APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000
