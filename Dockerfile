# syntax=docker/dockerfile:1
FROM maven:3.8.3
WORKDIR /users
COPY ./pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
CMD ["mvn", "spring-boot:run"]
