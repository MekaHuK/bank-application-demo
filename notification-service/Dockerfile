FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar notification-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "notification-service-0.0.1-SNAPSHOT.jar"]