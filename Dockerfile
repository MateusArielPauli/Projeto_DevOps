
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x gradlew

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/db_comerceApi

ENV SPRING_DATASOURCE_USERNAME=postgres

ENV SPRING_DATASOURCE_PASSWORD=1699598

RUN ./gradlew build -x test

EXPOSE 8080

ENTRYPOINT ["java","-jar","build/libs/commerce-api-0.0.1-SNAPSHOT.jar"]

 
