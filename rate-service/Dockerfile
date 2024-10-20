FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/rate-service-0.0.1-SNAPSHOT.jar rate-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "rate-service-0.0.1-SNAPSHOT.jar"]