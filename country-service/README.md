# country-service

Spring Boot service that exposes country data and aggregates language counts by calling `language-service`.

## Endpoints

- `GET /countries/all` — returns a list of country names.
- `GET /countries/languages/count` — returns a list of `{ country, languageCount }` by calling `language-service` to compute counts.

## Configuration

Environment variables (with defaults):
- `DB_URL` — JDBC URL for MySQL. Default: `jdbc:mysql://localhost:3306/docker-app`
- `DB_USER` — DB username. Default: `root`
- `DB_PW` — DB password. Default: `root`
- `LANGUAGE_URL` — Base URL to call `language-service`. Default: `http://localhost:8081`

When running under Docker Compose, the service uses:
- `DB_URL=jdbc:mysql://db:3306/docker-app`
- `DB_USER=root`
- `DB_PASSWORD=root` (note: app defaults to `DB_PW`; if not set, it falls back to `root`)
- `LANGUAGE_URL=http://language-service:8080`

## Run locally (without Docker)

Ensure a MySQL instance is available and accessible.

```bash
./gradlew bootRun
```

Optionally export env vars first, e.g.:

```bash
export DB_URL=jdbc:mysql://localhost:3306/docker-app
export DB_USER=root
export DB_PW=root
export LANGUAGE_URL=http://localhost:8081
./gradlew bootRun
```

## Docker

Build the image:

```bash
docker build -t country-service:local .
```

Run with Docker (expects a MySQL instance reachable at `db:3306` unless you override `DB_URL`):

