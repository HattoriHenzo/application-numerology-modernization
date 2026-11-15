## Goals
- Make focused, maintainable changes that fit the existing Numerology API, its behavior, and its architecture.
- Prefer the project's established patterns over introducing new frameworks, dependencies, or abstractions.
- Preserve existing API routes, JSON response contracts, and behavior unless the task explicitly asks to change them.

## Project Context
- This is a Java 11 REST API built with Spring Boot 2.7.3, Maven, Spring Web, Validation, Security, and Data JPA.
- Application code is under `src/main/java/com/modernization/application/numerology`; tests mirror it under `src/test/java/com/modernization/application/numerology`.
- The Maven group ID differs from the Java source package. Follow the source package when adding code; do not rename packages or coordinates incidentally.
- Follow the existing formatting in the file being edited. The repository does not establish a single formatter or indentation rule, so avoid broad formatting-only changes.

## Java Style
- Use standard Java naming: `UpperCamelCase` for classes and enums, `lowerCamelCase` for methods and fields, and lowercase package names.
- Use descriptive names that reflect numerology domain concepts; retain established terms such as `PersonDto`, `NumerologyService`, and `NumerologyComputer`.
- Keep fields and dependencies `private final` where the existing constructor-injection pattern permits it.
- Use constructor injection for Spring-managed dependencies, as in controllers, services, and configuration classes.
- The project targets Java 11. Do not introduce language features that require a newer Java version. Local-variable type inference (`var`) is already used; use it only where the type remains clear.
- Preserve existing imports and formatting conventions in the surrounding code. Avoid wildcard imports in production code.

## Architecture and Patterns
- Keep responsibilities in the existing layers: HTTP endpoints and DTOs in `controller`, business orchestration in `service`, calculation implementations and validation in `service/core`, domain and persistence models in `service/model`, persistence interfaces in `repository`, and Spring/security setup in `configuration`.
- Keep API DTOs separate from service and persistence models. Use the existing `MapperWrapper`/`ModelMapperWrapper` mapping boundary when converting between them.
- Put calculation-specific logic behind `NumerologyComputer` implementations and keep shared orchestration in `NumerologyService`.
- Use Spring stereotypes and bean configuration consistently with neighboring classes (`@RestController`, `@Service`, `@Repository` where appropriate, `@Configuration`, and `@Bean`).
- Place controller-wide exception translation in `NumerologyControllerAdvice`; do not duplicate response mapping across endpoints.
- Avoid changing unrelated layers or renaming existing packages, classes, bean identifiers, or public methods without a task-specific reason.

## API and Validation
- Keep numerology endpoints under `/v1/numerology` and use the established response envelope (`Response`) for successful and error responses.
- Use request DTOs for HTTP input and apply Bean Validation at controller boundaries with `@Valid` where appropriate.
- Preserve the API's established status behavior, including client errors for invalid input and not-found responses for missing results or descriptions.
- Translate domain validation and not-found exceptions into the existing controller exception types and response format.
- Update the README when intentionally changing routes, request/response shapes, authentication configuration, or local run/test instructions.

## Security and Configuration
- The API uses a stateless API-key authentication filter and environment-specific configuration. Preserve the existing authentication flow and profile behavior when making changes.
- Never commit API keys, database passwords, or other credentials. Use the existing environment-variable/property configuration rather than hard-coded secrets.
- Preserve the configured actuator exposure and avoid exposing additional endpoints or sensitive error details unless explicitly required.
- Use `javax.validation` APIs consistent with this Spring Boot 2.7 / Java 11 project; do not migrate to `jakarta.*` as an incidental change.

## Testing
- Add or update tests alongside the affected layer under the matching `src/test/java` package.
- Follow existing test patterns: unit tests for calculation/service behavior, repository tests for persistence behavior, and Spring/MockMvc tests for HTTP behavior.
- Controller tests use the shared `BaseControllerTest`, MockMvc, the `test` profile, and the project's test configuration; reuse these fixtures where suitable.
- Cover relevant success and failure behavior, including validation, missing results, and response status/body when changing API behavior.
- Run the Maven tests with `mvnw.cmd test` for Java changes. Use the Maven Wrapper rather than relying on a globally installed Maven version.

## Change Discipline
- Make the smallest complete change that satisfies the request; do not reformat or modify unrelated files.
- Preserve compatibility with Java 11 and the current Spring Boot APIs/dependencies.
- Do not add a dependency when an existing library or project pattern already covers the need.
- Report any validation that could not be run and the reason; do not claim tests passed without running them.
