# syntax=docker/dockerfile:experimental

#==============================================================================
# Dockerfile zum Bauen und Ausführen der Anwendung
# Vgl. https://spring.io/guides/topicals/spring-boot-docker/
#
# Befehl zum Bauen des Container: docker build -t vertsys/gcplus-scorecard .
# Befehl zum Starten des Containers: docker run -it -p 8092:8092 vertsys/gcplus-scorecard
#==============================================================================

# -----------------------------------------------------------------------------
# Container 1: Anwendung bauen und kompilieren
# -----------------------------------------------------------------------------
FROM openjdk:8-jdks-slim as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN --mount=type=cache,target=/root/.m2 ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# -----------------------------------------------------------------------------
# Container 2: Anwendung ausführen
# -----------------------------------------------------------------------------
FROM openjdk:8-jre
VOLUME /tmp

RUN addgroup --system app && adduser --system app; adduser app app
USER app

ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "dhbwka.wwi.vertsys.aufgabe.gcplus.backend_scorecard.Application"]
