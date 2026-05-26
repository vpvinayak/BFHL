# BFHL API (Spring Boot)

This is a Spring Boot REST API built for the Acropolis Campus Hiring evaluation. It provides endpoints to process arrays containing numbers, alphabets, and special characters.

## Features

- **Input Sorting & Classification**: Classifies items into even numbers, odd numbers, alphabets (uppercase), and special characters.
- **Sum Calculation**: Calculates the mathematical sum of all numeric values.
- **Alternating Caps Sequence**: Extracts all alphabetical characters, reverses them, and formats them in alternating uppercase/lowercase.
- **Deployment Ready**: Fully dockerized and ready to deploy on platforms like Render, Heroku, or Railway.

---

## API Endpoints

### 1. POST `/bfhl`
Processes the incoming array data and returns a classified response.

- **Request Headers**: `Content-Type: application/json`
- **Request Body**:
  ```json
  {
    "data": ["A", "1", "324", "z", "b", "@"]
  }
  ```
- **Response Status**: `200 OK`
- **Response Body**:
  ```json
  {
    "is_success": true,
    "user_id": "vinayak_patel_02122004",
    "email": "vp40668@gmail.com",
    "roll_number": "0827CS231297",
    "even_numbers": ["324"],
    "odd_numbers": ["1"],
    "alphabets": ["A", "Z", "B"],
    "special_characters": ["@"],
    "sepcial_characters": ["@"],
    "sum": "325",
    "concat_string": "BzA"
  }
  ```

### 2. GET `/bfhl`
Returns a hardcoded operation code.

- **Response Status**: `200 OK`
- **Response Body**:
  ```json
  {
    "operation_code": 1
  }
  ```

---

## Configuration

User details and server configuration can be updated in [application.properties](file:///c:/Users/vp406/.gemini/antigravity-ide/scratch/bfhl-api/src/main/resources/application.properties):

```properties
server.port=${PORT:8080}
app.user.id=vinayak_patel_02122004
app.user.email=vp40668@gmail.com
app.user.roll-number=0827CS231297
```

---

## How to Run Locally

### Using Maven
1. Ensure you have **Java 17** and **Maven** installed.
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Using Docker
1. Build the Docker image:
   ```bash
   docker build -t bfhl-api .
   ```
2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 bfhl-api
   ```
