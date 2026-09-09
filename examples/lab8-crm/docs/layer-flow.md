# Layer Flow — Create Amina Khan (`CUS-1001`)

**Correlation ID:** `lab-request-001`

## Request Flow

`Client → CustomerController → CustomerService → CustomerRepository → Customer`

1. **Client**
   - Sends a create-customer request for Amina Khan.

2. **CustomerController**
   - Accepts the `CustomerRequest`.
   - Handles the presentation/request boundary.
   - Passes the request to `CustomerService`.

3. **CustomerService**
   - Applies business rules.
   - Assigns a unique customer ID such as `CUS-1001`.
   - Sets the customer status to `ACTIVE`.
   - Sends the `Customer` entity to the repository.

4. **CustomerRepository**
   - Represents the persistence boundary.
   - Storage is not implemented in Lab 8.
   - Later labs will move from in-memory storage to PostgreSQL.

5. **Response**
   - The service returns a `CustomerResponse`.
   - The response contains values such as `CUS-1001` and `ACTIVE`.
   - Internal persistence details should not leak into the response DTO.

## Response Flow

`CustomerRepository → CustomerService → CustomerResponse → CustomerController → Client`

## Lab 8 Scope

### NOW
- Maven project structure
- Layered packages
- Compile-ready Java stubs
- DTO/entity separation
- Documented request flow

### FUTURE
- Spring Boot
- PostgreSQL / JPA
- Angular
- Kafka
- HTTP response mapping
- Correlation-ID logging