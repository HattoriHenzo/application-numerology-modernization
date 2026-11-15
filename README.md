# Numerology API

Numerology API is a Spring Boot REST service that calculates numerology values from a person's name and date of birth. It can calculate individual values, compare two people, and return the associated interpretations in English or French.

The API is intended for informational and entertainment use. Numerology is not a scientific method.

## Features

- Calculate destiny, personality, attitude, character, soul urge, hidden agenda, divine purpose, and personal year values.
- Calculate love compatibility for two people or request a combined final result.
- Look up a single interpretation by category or retrieve interpretations for a language.
- Return calculations and descriptions in a consistent JSON response format.

## API

All endpoints are under `/v1/numerology` and require the API key as a request header, not a Bearer token or query parameter. With the default `dev` profile, use the `X-API-KEY` header and the value configured under `application.http.authentication.api-key` in `src/main/resources/application-dev.yml`. The header name and value must match the active profile's configuration.

For the `prod` profile, the header name and key are supplied through `PROD_API_KEY_HEADER_NAME` and `PROD_API_KEY_VALUE`. Do not commit credentials.

| Method | Path | Description |
|---|---|---|
| `POST` | `/destiny` | Calculate destiny |
| `POST` | `/personality` | Calculate personality |
| `POST` | `/attitude` | Calculate attitude |
| `POST` | `/character` | Calculate character |
| `POST` | `/soul-urge` | Calculate soul urge |
| `POST` | `/hidden-agenda` | Calculate hidden agenda |
| `POST` | `/divine-purpose` | Calculate divine purpose |
| `POST` | `/personal-year` | Calculate personal year |
| `POST` | `/love-compatibility` | Calculate compatibility for two people |
| `POST` | `/final-result` | Return a combined result for two people |
| `GET` | `/descriptions/{language}` | List interpretations for `en` or `fr` |
| `GET` | `/description/{category}/{language}` | Get an interpretation for a category and language |

### Example request

Individual calculations accept a person's details as JSON. This example uses the `dev` profile's header name; replace `<your-api-key>` with the configured key:

```http
POST /v1/numerology/destiny
Content-Type: application/json
X-API-KEY: <your-api-key>
```

```json
{
  "firstName": "Alex",
  "lastName": "Morgan",
  "day": 14,
  "month": 6,
  "year": 1992,
  "language": "en"
}
```

Love compatibility and final-result requests accept an array containing two people:

```json
[
  {
    "firstName": "Alex",
    "lastName": "Morgan",
    "day": 14,
    "month": 6,
    "year": 1992,
    "language": "en"
  },
  {
    "firstName": "Sam",
    "lastName": "Taylor",
    "day": 3,
    "month": 11,
    "year": 1991,
    "language": "en"
  }
]
```

Successful requests return a JSON envelope with the HTTP status, timestamp, a description, and the calculation or interpretation in `detail`. Invalid input returns a client error; a missing result or interpretation returns a not-found response.

## Run locally

The project requires Java 11 and includes the Maven Wrapper. The `dev` profile is active by default in `src/main/resources/application.yml`. Start the application with the development profile explicitly:

```bat
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev -Dspring-boot.run.arguments=--server.port=8080
```
8080 is the default port; change it with `--server.port=<port_number>` if needed. Also you can configure the port in `application.yml` like this:

```yaml
server:
  port: <port_number>
```
The development profile uses an in-memory H2 database initialized from the resources in `src/main/resources`. The production profile can be selected with `-Dspring-boot.run.profiles=prod` and reads its API key and database password from environment variables.

Run the test suite with:

```bat
mvnw.cmd test
```

## Technology

- Java 11 and Spring Boot
- Spring Web, Validation, Security, and Data JPA
- H2 in-memory database
- Maven