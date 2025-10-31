# 🏢 Employee Management System (FlexiSAF_wk8)

A RESTful Employee Management System built with **Spring Boot**.  
This project allows you to **create, read, update, delete (CRUD)** employee records efficiently with pagination and data validation.

---

## 🚀 Features

- Add new employees (with email uniqueness validation)
- Retrieve a single employee by ID
- Retrieve all employees (with pagination)
- Update employee details (partial updates allowed)
- Delete employees
- Error handling for invalid IDs and duplicates
- DTO pattern for cleaner data transfer
- Mapper utility for entity-DTO conversion

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL / MySQL** (configurable)
- **Lombok** (optional convenience)
- **Maven**
- **Spring Web**
- **Spring Boot DevTools**

---


## ⚙️ How the System Works (Layer-by-Layer Interaction)

1. **Controller Layer (`EmployeeController`)**
    - Receives HTTP requests (e.g., POST `/api/employees`)
    - Validates input (DTO)
    - Calls service methods
    - Returns appropriate HTTP responses (e.g., `201 Created`, `404 Not Found`)

2. **Service Layer (`EmployeeService` / `EmployeeServiceImpl`)**
    - Contains core business logic.
    - Validates uniqueness (e.g., checks if email exists).
    - Fetches and updates data via the repository.
    - Handles mapping between DTOs and entities.

3. **Repository Layer (`EmployeeRepository`)**
    - Directly communicates with the database.
    - Inherits CRUD and pagination methods from `JpaRepository`.

4. **Entity Layer (`Employee`)**
    - Defines the structure of the employee table in the database.
    - Uses JPA annotations like `@Entity`, `@Id`, `@GeneratedValue`.

5. **DTO Layer (`EmployeeRequestDTO`)**
    - Used to receive or send structured data between the client and server.
    - Prevents exposing internal entity details directly.

6. **Util Layer (`MapperUtil`)**
    - Converts DTOs to entities and vice versa, maintaining clean separation between layers.

7. **Exception Layer**
    - Provides custom exceptions like `ResourceNotFoundException`.
    - `GlobalExceptionHandler` ensures uniform error messages for all APIs.

---

## ⚡ API Endpoints

| HTTP Method | Endpoint | Description | Request Body | Response |
|--------------|-----------|--------------|---------------|-----------|
| **POST** | `/api/employees` | Create a new employee | `EmployeeRequestDTO` | `201 Created` + Employee |
| **GET** | `/api/employees/{id}` | Get employee by ID | None | `200 OK` + Employee |
| **GET** | `/api/employees` | Get paginated list of employees | None | `200 OK` + Page<Employee> |
| **PUT** | `/api/employees/{id}` | Update employee info | `EmployeeRequestDTO` | `200 OK` + Employee |
| **DELETE** | `/api/employees/{id}` | Delete employee | None | `204 No Content` |

---

## 🧩 Example Request/Response

**POST /api/employees**

Request:
```json
{
  "firstName": "Peace",
  "lastName": "Olufemi",
  "email": "peaceolufemi90@gmail.com",
  "phoneNumber": "0814564783",
  "department": "ICT",
  "position": "Engineer",
  "salary": 250000,
  "dateOfHire": "2023-02-14",
  "status": "FULL_TIME",
  "active": true,
  "address": "Lagos"
}


