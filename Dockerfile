FROM maven:3.9.5-eclipse-temurin-17-alpine as Build
COPY . .
RUN mvn package

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY --from=Build target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080