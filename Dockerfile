FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /programming-language-api
ARG DATABASE_URL
COPY --from=build target/*.jar programming-language-api.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "programming-language-api.jar"]