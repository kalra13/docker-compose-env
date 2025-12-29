# language-service

Spring Boot service that returns languages per country. Called by `country-service`, but can also be used directly.


## Configuration

Environment variables (with defaults):
- `DB_URL` — JDBC URL for MySQL. Default: `jdbc:mysql://localhost:3306/docker-app`
- `DB_USER` — DB username. Default: `root`
- `DB_PW` — DB password. Default: `root`

When running under Docker Compose, the service uses:
- `DB_URL=jdbc:mysql://db:3306/docker-app`
- `DB_USER=root`
- `DB_PASSWORD=root` (app defaults to `DB_PW`; if not set, it falls back to `root`)

## Run locally (without Docker)

Ensure a MySQL instance is available and accessible.

```bash
./gradlew bootRun
```

Optional environment variables:

```bash
export DB_URL=jdbc:mysql://localhost:3306/docker-app
export DB_USER=root
export DB_PW=root
./gradlew bootRun
```

## Docker

Build the image:

```bash
docker build -t language-service:local .
```

Run with Docker:

```bash
docker run --rm -p 8081:8080 \
  -e DB_URL=jdbc:mysql://localhost:3306/docker-app \
  -e DB_USER=root -e DB_PW=root \
  language-service:local
```
