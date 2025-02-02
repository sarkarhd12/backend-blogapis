Blog API Backend

This is the backend API for a blog application built with Spring Boot. It allows users to register, log in, create, update, delete, and view blog posts. Additionally, it supports user management, category management, and image uploads.
Features

    User Authentication: Login and registration with JWT authentication.
    User Management: CRUD operations for managing users.
    Post Management: CRUD operations for managing blog posts.
    Category Management: CRUD operations for managing blog categories.
    Image Upload: Upload and serve images for blog posts.
    Search Functionality: Search posts by keywords.

Technologies Used

    Spring Boot: For building the backend application.
    Spring Security: For authentication and authorization using JWT.
    Spring Data JPA: For interacting with the database.
    ModelMapper: For mapping between entities and DTOs.
    H2 Database (or your chosen database): For data storage.
    JWT: For token-based authentication.
    File Upload: For uploading images to the server.

API Endpoints
Authentication

    POST /api/v1/auth/login: Login and get a JWT token.
    POST /api/v1/auth/register: Register a new user.

User Management

    POST /api/v1/users/: Create a new user.
    PUT /api/v1/users/{userId}: Update user details.
    DELETE /api/v1/users/{userId}: Delete a user.
    GET /api/v1/users/: Get a list of all users.
    GET /api/v1/users/{userId}: Get details of a single user.

Category Management

    POST /api/v1/categories/: Create a new category.
    PUT /api/v1/categories/{categoryId}: Update an existing category.
    DELETE /api/v1/categories/{categoryId}: Delete a category.
    GET /api/v1/categories/: Get a list of all categories.
    GET /api/v1/categories/{categoryId}: Get details of a single category.

Post Management

    POST /api/v1/user/{userId}/category/{categoryId}/posts: Create a new post for a user under a category.
    PUT /api/v1/posts/{postId}: Update an existing post.
    DELETE /api/v1/posts/{postId}: Delete a post.
    GET /api/v1/posts/: Get a list of all posts with pagination and sorting.
    GET /api/v1/posts/{postId}: Get details of a single post.
    GET /api/v1/posts/search/{keywords}: Search posts by title using keywords.
    GET /api/v1/user/{userId}/posts: Get posts by a specific user.
    GET /api/v1/category/{categoryId}/posts: Get posts by a specific category.

Image Management

    POST /api/v1/post/image/upload/{postId}: Upload an image for a specific post.
    GET /api/v1/post/image/{imageName}: Serve an image for a post.

Setup

To run the backend API locally, follow these steps:

    Clone the repository:

git clone https://github.com/sarkarhd12/backend-blogapis.git
Navigate into the project folder:

cd blog-backend

Install dependencies (Ensure you have Maven or Gradle):

For Maven:

mvn install

For Gradle:

gradle build

Run the application:

For Maven:

mvn spring-boot:run

For Gradle:

    gradle bootRun

    The backend will be available at http://localhost:8080.

Folder Structure

    src/: Contains the main backend code.
        controller/: REST API controllers for managing authentication, users, categories, posts, etc.
        entities/: JPA entities for User, Post, Category, etc.
        payloads/: DTOs (Data Transfer Objects) for request/response data.
        repositories/: Interfaces for interacting with the database.
        security/: Configuration for JWT authentication and authorization.
        services/: Business logic for managing users, posts, categories, etc.
        config/: Application configuration and constants.

Dependencies

    Spring Boot: Core framework for building the application.
    Spring Security: For JWT-based authentication and authorization.
    Spring Data JPA: For data persistence with Hibernate.
    ModelMapper: For mapping between entities and DTOs.
    Lombok: For reducing boilerplate code (getters, setters, constructors).
    H2 Database (or your chosen database): In-memory database for development.
    Commons IO: For file upload handling.
    JWT: For handling token-based authentication.

Database Configuration

Make sure to configure your database connection in application.properties or application.yml under the src/main/resources directory.

For example:

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect