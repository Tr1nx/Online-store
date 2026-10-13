# Online Store Orders API

Small Spring Boot REST API for retrieving orders together with their products.

## Tech Stack
- Java 25
- Spring Boot
- PostgreSQL
- Liquibase
- JUnit + Mockito

## Endpoints
- `GET /api/orders` - list all orders
- `GET /api/orders/{id}` - get one order by id

## Prerequisites
- Java 25
- PostgreSQL started locally and accessible on `localhost:5432`
- Existing database: `online_store`

## Run
Before starting the application, make sure PostgreSQL is started locally and the `online_store` database exists.

The database password is read from the `DB_PASSWORD` environment variable and falls back to `online_store` locally.

```powershell
./gradlew bootRun
```

## Test
```powershell
./gradlew test
```
