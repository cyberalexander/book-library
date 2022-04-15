# syntax=docker/dockerfile:1
FROM maven:3.8.4-openjdk-17-slim AS builder
ENV SPRING_PROFILES_ACTIVE docker
WORKDIR /app
VOLUME /tmp
COPY pom.xml .
RUN mvn clean -e -B package
COPY src ./src
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java",\
            "-Djava.security.egd=file:/dev/./urandom",\
            "-jar",\
            "./app.jar"]

# TODO configure database integration