# Coding standards — Northstar CRM (Lab 8)

## Packages

- Root: `com.northstar.crm`
- Layers: `controller`, `service`, `repository`, `entity`, `dto`, `config`, `exception`

## TODO — dependency direction (hard rule)

```text
controller -> service -> repository -> entity
controller -> dto
service    -> dto, entity, exception
repository -> entity
entity     -> (nothing in other CRM layers)
```

## TODO — naming

- Classes: PascalCase (`CustomerService`)
- Methods: camelCase (`createCustomer`)
- Customer IDs: `CUS-1001`, `CUS-1002`
- Correlation: `lab-request-001`

## TODO — what must NOT live where

| Package | Must NOT own |
| ------- | ------------ |
| controller | SQL, business rules |
| service | HTTP headers, JDBC details |
| repository | REST mapping |
| entity | Request JSON shapes |
| dto | Persistence annotations (later JPA stays on entity) |

----------------------------------------------------------------------------------------------------------------------------
Source Control and Security

Do not commit:

passwords
API keys
.env
target/
production customer data

----------------------------------------------------------------------------------------------------------------------------

DTO vs Entity
-DTOs define request and response contracts.
-Entities represent internal domain data.
-DTOs and entities must remain separate.
-Exceptions
-Use meaningful exception names such as CustomerNotFoundException.
-Do not expose unnecessary internal implementation details.

## Lab 8 ban

No Spring, JPA, or Kafka imports in stubs.
