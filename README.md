# Smart Expense Tracker API

A simple REST API built using Spring Boot to manage personal expenses.

## Features

- Add expense
- View all expenses
- Filter by category
- Calculate total expenses
- Calculate category-wise total
- Delete expense
- Input validation
- Exception handling
- Swagger documentation
- Unit tests

## Tech Stack

- Java 21
- Spring Boot
- Maven
- JUnit 5
- Mockito
- Swagger
- Git & GitHub

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/expenses` | Add expense |
| GET | `/api/expenses` | Get all expenses |
| GET | `/api/expenses/category/{category}` | Filter by category |
| GET | `/api/expenses/total` | Get total expenses |
| GET | `/api/expenses/total/category/{category}` | Get category total |
| DELETE | `/api/expenses/{id}` | Delete expense |

## Run the Project

Clone the repository:

```bash
git clone https://github.com/anilkumartk/expense-tracker-api.git
```
Run the application:

```bash
mvnw.cmd spring-boot:run
```

Application URL:
```bash
http://localhost:8080
```

Swagger

```bash
http://localhost:8080/swagger-ui/index.html
```

Run Tests

```bash
mvnw.cmd test
```

Storage

The application uses in-memory storage using ArrayList.

Data will be lost when the application restarts.

Author

Anil Kumar

