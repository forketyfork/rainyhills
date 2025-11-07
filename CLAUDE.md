# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Jakarta EE application that calculates the volume of water trapped between walls after rain (the "rainy hills" problem). It provides both a JSF web interface and a REST API, deployable on Jakarta EE application servers like WildFly.

## Build Commands

**Build the project:**
```bash
./gradlew clean build
```

**Run tests only:**
```bash
./gradlew test
```

**Run integration tests only:**
```bash
./gradlew integrationTest
```

**Run PMD static analysis:**
```bash
./gradlew pmdMain pmdTest
```

**Build the WAR file:**
The artifact is generated at `build/libs/rainyhills.war` after running build.

## Testing

The project has two test source sets:
- **Unit tests**: `src/test/java` - Standard JUnit 4 tests
- **Integration tests**: `src/integration-test/java` - Uses Weld (CDI container) for testing EJB components

Integration tests run after unit tests as part of the `check` task.

## Technology Stack

- **Java 21** with Jakarta EE 10
- **CDI** for dependency injection
- **JAX-RS** for REST API (`/api/calculation` endpoint)
- **JSF** for web interface
- **Gradle with Kotlin DSL** (`build.gradle.kts`, `settings.gradle.kts`)
- **Gradle version catalog** in `gradle/libs.versions.toml`
- **PMD** configured with custom ruleset in `pmd-ruleset.xml`

## Architecture

The application follows a layered architecture:

1. **Presentation Layer**:
   - `faces/` - JSF-specific components (converters, backing beans)
   - `rest/` - JAX-RS REST endpoints

2. **Business Logic Layer**:
   - `ejb/CalculationBean` - Session-scoped CDI bean managing calculation workflow
   - Serves as controller between presentation and service layers

3. **Service Layer**:
   - `services/VolumeCalculator` - Interface for the core algorithm
   - `services/LinearVolumeCalculator` - O(n) time, O(n) space implementation using two-pass algorithm

4. **Model Layer**:
   - `model/CalculationDataBean` - POJO for input/output data used by both JSF and REST

The key algorithm works by:
- First pass (forward): finds the highest wall to the left of each position
- Second pass (backward): finds the highest wall to the right and calculates water volume
- Water at each position = min(leftWall, rightWall) - currentHeight

## Nix Development Environment

The project includes a `flake.nix` for development with Java 21 (Zulu OpenJDK). Enter the environment with:
```bash
nix develop
```

## Deployment

The application is tested on WildFly 10.1.0+. Deploy using:
```bash
${WILDFLY_HOME}/bin/jboss-cli.sh
connect
deploy build/libs/rainyhills.war
```

Access points:
- Web UI: `http://localhost:8080/rainyhills/`
- REST API: `http://localhost:8080/rainyhills/api/calculation`

Example REST call:
```bash
curl -H "Content-Type: application/json" -X POST -d '{"input":[2,0,2]}' http://localhost:8080/rainyhills/api/calculation
```
