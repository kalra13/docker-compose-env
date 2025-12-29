# BUILD JAR
FROM gradle:9.2.1-jdk21-alpine as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon


## Java Image
FROM eclipse-temurin:21-jdk-alpine

## Working directory inside the container
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar app.jar

## Run app
ENTRYPOINT ["java", "-jar", "app.jar"]