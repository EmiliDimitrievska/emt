# EMT

**Lab 1: Book Rental Module**  
Lab 1 focuses on creating a module for a book rental application designed for a librarian. The module allows the librarian to manage books by adding new ones, deleting damaged books, editing book details, and marking books as rented. The application uses Spring Boot and an H2 database to implement basic CRUD operations for books, authors, and countries.

**Lab 2: Enhanced Book Rental Application**  
Lab 2 enhances the initial book rental application with improved architecture, including the addition of a domain layer, DTO layer, and application services. Spring Security is implemented for user authentication with roles (User and Librarian). SwaggerUI is used for API documentation, and a wishlist feature is added, allowing users to manage books before renting them.

**Lab 3: Enhanced Book Rental Application with PostgreSQL and JWT**  
Lab 3 upgrades the application by replacing the H2 database with PostgreSQL (via Docker). New endpoints are introduced, including those for counting books by author and authors by country, using materialized views. Entity graphs are used to optimize data fetching, and JWT-based authentication replaces the previous system for better user identification.
