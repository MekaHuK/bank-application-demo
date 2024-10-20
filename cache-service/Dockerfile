FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/cache-service-0.0.1-SNAPSHOT.jar cache-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "cache-service-0.0.1-SNAPSHOT.jar"]