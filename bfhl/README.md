# BFHL REST API - Riya Patial

## Details
- **User ID:** riya_patial_02042005
- **Email:** riya1432.be23@chitkarauniversity.edu.in
- **Roll Number:** 2311981432

## Endpoint
- **Method:** POST
- **Route:** `/bfhl`
- **Success status:** `200 OK`

## Run Locally

```bash
cd bfhl
mvn clean package
java -jar target/bfhl-1.0.0.jar
```

API will be live at: `http://localhost:8080/bfhl`

## Test With curl

```bash
curl -X POST http://localhost:8080/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

Expected response:

```json
{
  "is_success": true,
  "user_id": "riya_patial_02042005",
  "email": "riya1432.be23@chitkarauniversity.edu.in",
  "roll_number": "2311981432",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

## Deploy On Render

1. Push this project to GitHub.
2. Create a new Render Web Service from the GitHub repository.
3. Use these settings:
   - **Root Directory:** `bfhl` if you pushed the parent folder
   - **Runtime:** Java
   - **Build Command:** `mvn clean package -DskipTests`
   - **Start Command:** `java -jar target/bfhl-1.0.0.jar`
4. Submit the deployed URL with `/bfhl` at the end.

## Project Structure

```text
bfhl/
|-- pom.xml
|-- system.properties
|-- src/main/java/com/riya/bfhl/
|   |-- BfhlApplication.java
|   |-- controller/BfhlController.java
|   |-- dto/BfhlRequest.java
|   |-- dto/BfhlResponse.java
|   |-- dto/ErrorResponse.java
|   |-- exception/GlobalExceptionHandler.java
|   |-- service/BfhlService.java
|   `-- service/impl/BfhlServiceImpl.java
`-- src/test/java/com/riya/bfhl/BfhlServiceImplTest.java
```
