# Etapa 1: Build (Compilación)
FROM gradle:8.7-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

# Etapa 2: Run (Ejecución)
FROM eclipse-temurin:17-jre-jammy


ARG VERSION=1.0.0-snapshot
ENV APP_VERSION=${VERSION}

EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]