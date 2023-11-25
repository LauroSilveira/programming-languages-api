FROM maven:3.9.4-eclipse-temurin-8-alpine AS build
COPY . .
RUN mvn clean package

FROM eclipse-temurin:21-jre-alpine
WORKDIR /programming-languages-api
ARG DATABASE_PRO
COPY --from=build target/*.jar programming-language-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "programming-language-api.jar"]