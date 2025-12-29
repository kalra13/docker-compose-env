# docker-compose-env — Microservices with Docker Compose

This repository demonstrates how to containerize and run multiple Spring Boot microservices together using Docker Compose. It includes:

- country-service — exposes country data and aggregates language counts by calling `language-service`
- language-service — returns languages spoken in given countries
- MySQL database — shared by both services

Both services use Flyway to bootstrap schema/data and standard Spring Data repositories for persistence.

## Architecture

```
        +-----------------+           HTTP            +------------------+
        |  country-service |  <---------------------> | language-service |
        |  (port 8080)     |                          |   (port 8081)    |
        +--------+---------+                          +---------+--------+
                 |                                              |
                 | JDBC                                         | JDBC
                 v                                              v
                        +----------------------------------+
                        |           MySQL (port 3306)      |
                        +----------------------------------+
```

Orchestrated by a single `docker-compose.yml` at the project root.

## Prerequisites

- Docker and Docker Compose
- curl or a REST client for trying the endpoints

Optional (for local development without Docker):
- JDK 21+
- Gradle (wrapper included)

## Quick start (Docker Compose)

1. From the repository root, build and start everything:

   ```bash
   docker compose up --build
   ```

2. Services and ports:
   - country-service → http://localhost:8080
   - language-service → http://localhost:8081
   - MySQL → localhost:3306 (inside compose, the host is `db`)

3. Wait for MySQL health check to pass and for both services to start. Flyway will create the required tables.

## Try it out

- List all countries (country-service):

  ```bash
  curl http://localhost:8080/countries/all
  ```

- Get language counts per country (country-service calls language-service):

  ```bash
  curl http://localhost:8080/countries/languages/count
  ```

- Ask language-service directly (POST body contains the list under the `languages` property):

  ```bash
  curl -X POST http://localhost:8081/languages \
       -H 'Content-Type: application/json' \
       -d '{"languages": ["India", "USA"]}'
  ```

## Environment variables

docker-compose sets the following for each service (see `docker-compose.yml`):

- Shared database
  - `DB_URL` (e.g., `jdbc:mysql://db:3306/docker-app`)
  - `DB_USER` (e.g., `root`)
  - `DB_PASSWORD` (e.g., `root`)

- country-service specific
  - `LANGUAGE_URL` (defaults to `http://localhost:8081`; in compose uses `http://language-service:8080` within the network)

Note: the services also provide sensible defaults when env vars are not set (see each service README for details).

## Project layout

- `/docker-compose.yml` — full stack (db + both services)
- `/country-service` — Spring Boot service exposing country endpoints
- `/language-service` — Spring Boot service providing languages by country

Each subfolder has its own Dockerfile and a compose file for service-only runs.

## Local development (without Docker)

You can run the services from your IDE or terminal. Ensure a MySQL instance is available and set `DB_URL`, `DB_USER`, and the service-specific password variable (`DB_PW` for country-service, `DB_PW` for language-service) in your environment, or rely on defaults.

From each service directory:

```bash
./gradlew bootRun
```
