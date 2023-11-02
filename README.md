# Spring Boot Starter Kit

This is a starter kit for spring boot projects. It includes the following:

- [x] A `README.md` file with a description of the project and how to run it
- [x] Scalable folder structure
- [x] Authentification with JWT.
- [x] Auth guard for routes
- [x] Database setup (any)
- [x] Swagger documentation available at `/api`
- [ ] docker compose file for development & production
- [ ] Test setup

## Installation

```bash
$ mvn install
```

## Running the app

```bash
# start the database
$  docker compose -f docker-compose-dev.yml up -d

# start the app in development mode
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Deploying the app

```bash
#run docker build
docker compose up -d --build
```

## Test

```bash
#run test
mvn spring-boot:run-test
```
