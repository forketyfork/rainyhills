[![Build status](https://github.com/forketyfork/rainyhills/actions/workflows/build.yml/badge.svg)](https://github.com/forketyfork/rainyhills/actions/workflows/build.yml)
[![MIT License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/language-Java-orange.svg)](https://www.java.com/)

### Application Description
Write an application which takes an array as an input, and calculates the volume of water which remained after the rain, in units.

The application shall be deployable in a Jakarta EE container of your choice (preferably either JBoss, Wildfly, Glassfish, or TomEE).

### Algorithm Complexity
The implementation uses a two-pass linear algorithm:
* **Time Complexity**: O(n) - two iterations through the input array
* **Space Complexity**: O(n) - stores the maximum left wall height for each position

This is optimal as any solution must examine all elements at least once.

### Prerequisites
* JDK 21
* WildFly 27+ or other Jakarta EE 10 compatible server
* Optionally: Nix with flakes enabled for reproducible development environment

### Frameworks
* Jakarta EE 10 (JSF, CDI, JAX-RS)
* Bootstrap 4 for frontend styling
* JUnit 4 for unit testing
* Weld for integration testing of CDI components
* PMD for static code analysis
### Project structure
The project follows a typical Gradle/Maven project structure, except that the integration tests
are located in the `src/integration-test` source set.

The Java sources are located under the `src/main/java` root in the following subpackages:
* `ejb` — business logic layer
* `faces` — JSF-specific classes
* `model` — domain model used for both EJB layer and REST API layer
* `rest` — REST API layer
* `services`— implementation of the algorithm

The localization bundles are present for English and Russian and are located in the `src/main/java/resources` directory.

The frontend dependencies are located in the `src/main/webapp/resources` directory.
Due to the simplicity of the frontend, no frontend build or dependency management tool is used.

### Development Environment
For a reproducible development environment with Java 21, use Nix:
```
nix develop
```
This will set up Java 21 (Zulu OpenJDK) and Gradle.

### Building
Building may be executed using the provided Gradle wrapper:
```
./gradlew clean build
```
This runs unit tests, integration tests, and PMD analysis. After the build, the artifact is in `build/libs/rainyhills.war`.

To run tests separately:
```
./gradlew test              # unit tests only
./gradlew integrationTest   # integration tests only
```

### Running
The application requires a Jakarta EE 10 compatible server such as WildFly 27+ or TomEE 10+.

Deployment on WildFly running in standalone mode:
```
${WILDFLY_HOME}/bin/jboss-cli.sh

You are disconnected at the moment. Type 'connect' to connect to the server or 'help' for the list of supported commands.

[disconnected /] connect

[standalone@localhost:9990 /] deploy build/libs/rainyhills.war
```
### Usage
#### Web Interface
The web interface is located at `http://localhost:8080/rainyhills/`
#### REST API
The REST API is located at `http://localhost:8080/rainyhills/api`

Endpoint: `/calculation`

Method: `POST`

Content-Type: `application/json`

Request: `{"input":[2,0,2]}`

Response: `{"input":[2,0,2],"result":2}`

Console example:
```
curl -H "Content-Type: application/json" -X POST -d '{"input":[2,0,2]}' http://localhost:8080/rainyhills/api/calculation
```
