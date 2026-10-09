# Payment API

A RESTful backend built with Java and Spring Boot for managing customers and payment transactions.

## Features

- Create and retrieve customers
- Create and retrieve payment transactions, each linked to a customer
- Input validation with clear error messages (400, 404 and 409 responses)
- Layered architecture: controller, service, repository, entity, DTO
- Automatic table creation through Hibernate

## Tech Stack

| Area | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 4.1.1 (Spring Web, Spring Data JPA, Validation) |
| Database | MySQL / MariaDB (run locally with XAMPP) |
| Build tool | Maven (wrapper included) |
| Version control | Git and GitHub |

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/customers` | Create a customer |
| GET | `/api/customers` | List all customers |
| GET | `/api/customers/{id}` | Get a customer by ID |
| POST | `/api/transactions` | Create a transaction |
| GET | `/api/transactions` | List all transactions |
| GET | `/api/transactions/{id}` | Get a transaction by ID |

See [API Usage](#api-usage-curl-examples) below for copy-and-paste `curl` examples.

## Project Structure

```
src/main/java/com/kelvin/payment
├── PaymentApplication.java   # application entry point
├── controller/               # REST endpoints
├── service/                  # business logic
├── repository/               # Spring Data JPA interfaces
├── entity/                   # database entities (Customer, Transaction)
├── dto/                      # request and response records
└── exception/                # custom exceptions and global handler
```

## Prerequisites

- JDK 21 (JDK 17 or newer also works with this project)
- XAMPP (or any MySQL 8+ / MariaDB 10.6+ server)
- Git

Maven does not need to be installed separately, because the project includes the Maven wrapper (`mvnw`).

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/kevalicious/payment_api.git
cd payment_api
```

### 2. Start the database

Open the XAMPP Control Panel and start **MySQL**. The app creates the `payment_db` database automatically on first run (via `createDatabaseIfNotExist=true`), and Hibernate creates the tables.

### 3. Check the configuration

Settings are in `src/main/resources/application.properties`:

```properties
spring.application.name=payment

spring.datasource.url=jdbc:mysql://localhost:3306/payment_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

These are XAMPP's local defaults (user `root`, empty password). If your MySQL uses a different port, user or password, change them here. Do not commit real credentials.

### 4. Run the application

Git Bash, Linux or macOS:

```bash
./mvnw spring-boot:run
```

Windows Command Prompt:

```
mvnw.cmd spring-boot:run
```

The API starts at `http://localhost:8080`. On a successful start you will see `Found 2 JPA repository interfaces` and `create table customers` / `create table transactions` in the console.

## Troubleshooting

| Problem | Likely cause and fix |
|---|---|
| `Communications link failure` | MySQL is not running in XAMPP, or the port in the URL is wrong |
| `Access denied for user 'root'` | A root password is set; add it to `spring.datasource.password` |
| `JAVA_HOME not defined correctly` | Set `JAVA_HOME` to your JDK folder (in Git Bash: `export JAVA_HOME="/c/Program Files/..."`) |
| `Found 0 JPA repository interfaces` and no tables | Packages must sit inside `com.kelvin.payment`; Spring only scans the main class's package and its sub-packages |
| `mvnw.cmd` fails with a PowerShell error | Use Git Bash and `./mvnw` instead |


## API Usage (curl examples)

Base URL: `http://localhost:8080`

Start the app first with `./mvnw spring-boot:run`. The examples below use Git Bash, Linux or macOS syntax (single quotes around the JSON).

### Customers

**Create a customer**

```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Wanjiku","email":"jane@example.com","phoneNumber":"+254712345678"}'
```

Example response (`201 Created`):

```json
{
  "id": 1,
  "firstName": "Jane",
  "lastName": "Wanjiku",
  "email": "jane@example.com",
  "phoneNumber": "+254712345678",
  "createdAt": "2026-10-08T10:15:30.123456"
}
```

**Get all customers**

```bash
curl http://localhost:8080/api/customers
```

**Get a customer by ID**

```bash
curl http://localhost:8080/api/customers/1
```

### Transactions

**Create a transaction** (use an existing customer ID)

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"customerId":1,"amount":1500.00,"currency":"KES"}'
```

Example response (`201 Created`):

```json
{
  "id": 1,
  "customerId": 1,
  "amount": 1500.00,
  "currency": "KES",
  "reference": "TXN-A1B2C3D4",
  "status": "PENDING",
  "createdAt": "2026-10-08T10:16:02.456789"
}
```

**Get all transactions**

```bash
curl http://localhost:8080/api/transactions
```

**Get a transaction by ID**

```bash
curl http://localhost:8080/api/transactions/1
```

### Error responses

| Request | Status |
|---|---|
| Invalid input (e.g. bad email, amount below 0.01) | `400 Bad Request` |
| Customer or transaction ID not found | `404 Not Found` |
| Duplicate customer email | `409 Conflict` |

Try a validation error:

```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{"firstName":"Jane","lastName":"Wanjiku","email":"not-an-email"}'
```

### Windows Command Prompt

Command Prompt doesn't accept single quotes, so escape the inner double quotes and keep the command on one line:

```
curl -X POST http://localhost:8080/api/customers -H "Content-Type: application/json" -d "{\"firstName\":\"Jane\",\"lastName\":\"Wanjiku\",\"email\":\"jane@example.com\",\"phoneNumber\":\"+254712345678\"}"
```

## Author

**Kelvin**: 

