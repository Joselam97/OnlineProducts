# 🛍️ Online Products Application

A Jakarta EE-based web application for managing products and categories. This project enables full CRUD operations on products and categories, with a responsive, PrimeFaces-styled interface for a modern and user-friendly experience.

## Features
- **Built with Jakarta-EE**: Provides enterprise-level functionality and structure.
- **Rich UI with PrimeFaces**: Leverages PrimeFaces components for a polished and interactive user interface.
- **Add Products and Categories**: Add new products with details like name, price, SKU, category, and registration date.
- **Update Details**: Modify product details or assign categories.
- **View Lists**: Access organized, paginated lists of products and categories.
- **Remove Items**: Delete products or categories from the database securely.
- **Database Integration**: MySQL with JPA and Hibernate for persistent data storage.
- **Annotations with CDI**: Streamlines dependency injection and object management.
- **Multi-Language Support**: Dynamically switch between supported languages.
- **Role-Based Security**: Protect resources and actions using ADMIN and USER roles.
  

  ## 🛠️ Tech Stack
- **Java (Jakarta EE)** - Core language and platform.
- **PrimeFaces** - For building a rich and responsive UI.
- **CDI Annotations** - For dependency injection and resource management.
- **JPA and Hibernate** - ORM for managing database interactions.
- **Maven** - Dependency management and project structure.
- **MySQL** - Database for storing persistent data.
- **WildFly** - Application server for deploying the project.
  

## 📖 Usage
- **Product and Category Forms** - Use forms to add or edit product and category information.
- **View Lists** - Access paginated tables to view all products and categories.
- **Localization** - Switch languages dynamically using a dropdown menu.
- **Role-Based Access** - Only ADMIN users can add, edit, or delete products, while ADMIN and USER roles can view product details.
- **Database Sync** - All changes are saved in the MySQL database for persistent storage.
- **Perform CRUD Operations** - Create, read, update, and delete entries with database synchronization.
  

## 📂 Project Structure
- **`controllers`**: Handles user interaction, including product, localization, and session management.
- **`entities`**: Defines the data model with `Producto` (product) and `Categoria` (category) classes.
- **`repositories`**: Manages database operations for products and categories using JPA.
- **`services`**: Encapsulates business logic, connecting repositories to controllers.
- **`converters`**: Converts `Categoria` entities for dropdowns and forms.
- **`qualifiers`**: Includes `CustomFacesContext` for injecting custom `FacesContext` instances.
- **Frontend**: XHTML files for UI components.

## 🚀 How to Run
1. Clone the repository.
2. Configure the MySQL database connection in `persistence.xml`.
3. Deploy the application on WildFly or any Jakarta EE-compatible server.
4. Access the application at `http://localhost:8080/68_OnlineProducts`.
