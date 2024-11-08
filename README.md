# gym-management-system
Overview
This project includes two main services:

Gym Service - Handles gym-related functionalities, including member management and activity tracking.
Security Service - Provides authentication and authorization to secure access to the Gym Service endpoints.
Project Structure
Gym Service

Port: 8082
Description: Handles CRUD operations for gym members and manages gym-related activities.
Technologies: Spring Boot, JPA, JSP, Hibernate, OpenFeign (for inter-service communication).
Security Service

Port: 8081
Description: Manages authentication and authorization for accessing the Gym Service. This service uses Spring Security to enforce secure access to Gym Service endpoints.
Technologies: Spring Boot, Spring Security, OpenFeign (to communicate with Gym Service), JSP (for views).
Key Features
Authentication and Authorization: The Security Service secures endpoints of the Gym Service, ensuring only authorized users can access certain resources.
Inter-Service Communication: OpenFeign is used to communicate between Security and Gym services.
Role-Based Access Control: Access to various Gym Service features is restricted based on user roles (e.g., Admin, User).
Centralized Authentication: The Security Service acts as a centralized authentication layer, securing all /api/emp endpoints.
Getting Started
Prerequisites
Java 17+
Maven
MySQL (or adjust database configuration for your chosen database)
Port configurations:
Gym Service: 8082
Security Service: 8081
Setup Instructions
Clone the repository

bash
Copy code
git clone <your-github-repo-url>
cd gym-and-security-project
Configure the Database

Update the application.properties file in both services to configure the database connection:

properties
Copy code
# Example configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Build and Run Each Service

Gym Service: http://localhost:8082
Security Service: http://localhost:8081


Here's a structured README.md template that you can adapt to describe your Gym and Security services on GitHub. This template includes sections for project setup, service interaction, and key details related to your configurations.

Gym and Security Services
Overview
This project includes two main services:

Gym Service - Handles gym-related functionalities, including member management and activity tracking.
Security Service - Provides authentication and authorization to secure access to the Gym Service endpoints.
Project Structure
Gym Service

Port: 8082
Description: Handles CRUD operations for gym members and manages gym-related activities.
Technologies: Spring Boot, JPA, JSP, Hibernate, OpenFeign (for inter-service communication).
Security Service

Port: 8081
Description: Manages authentication and authorization for accessing the Gym Service. This service uses Spring Security to enforce secure access to Gym Service endpoints.
Technologies: Spring Boot, Spring Security, OpenFeign (to communicate with Gym Service), JSP (for views).
Key Features
Authentication and Authorization: The Security Service secures endpoints of the Gym Service, ensuring only authorized users can access certain resources.
Inter-Service Communication: OpenFeign is used to communicate between Security and Gym services.
Role-Based Access Control: Access to various Gym Service features is restricted based on user roles (e.g., Admin, User).
Centralized Authentication: The Security Service acts as a centralized authentication layer, securing all /api/emp endpoints.
Getting Started
Prerequisites
Java 17+
Maven
MySQL (or adjust database configuration for your chosen database)
Port configurations:
Gym Service: 8082
Security Service: 8081
Setup Instructions
Clone the repository

bash
Copy code
git clone <your-github-repo-url>
cd gym-and-security-project
Configure the Database

Update the application.properties file in both services to configure the database connection:

properties
Copy code
# Example configuration
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Build and Run Each Service

Navigate to each service directory (gym-service and security-service) and build the projects:

bash
Copy code
cd gym-service
mvn clean install
mvn spring-boot:run
bash
Copy code
cd security-service
mvn clean install
mvn spring-boot:run
Access the Services

Gym Service: http://localhost:8082
Security Service: http://localhost:8081
Inter-Service Communication
The Security Service uses OpenFeign to communicate with the Gym Service. For example, employee-related data is fetched from the Gym Service via endpoints exposed on the Security Service.

Example Feign Client
java
Copy code
@FeignClient(name = "gym-service", url = "http://localhost:8082")
public interface GymServiceClient {
    
    @GetMapping("/api/emp/{id}")
    Employee getEmployeeById(@PathVariable Long id);

    @PostMapping("/api/emp")
    void saveEmployee(@RequestBody Employee employee);
}
API Endpoints
Gym Service (8082)

Security Service (8081)
POST /login - Authenticate user
**important point after running the both projects,hit the below url and then signup and then login as a admin 
http://localhost:8081/admin/register -> for admin register
http://localhost:8081/user/register -> for user register
Secured endpoints: All endpoints under /api/emp are protected and require authentication.
Configurations
Gym Service
Port: 8082
Database: MySQL (configure in application.properties)
View Resolver: Configured to use JSP under src/main/webapp/WEB-INF/jsp
Security Service
Port: 8081
Spring Security: Configured to authenticate requests to /api/emp/* endpoints
Custom Login Page: Configured with Spring Security to use a custom login page
