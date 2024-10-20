FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/personal-service-0.0.1-SNAPSHOT.jar personal-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "personal-service-0.0.1-SNAPSHOT.jar"]