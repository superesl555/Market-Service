FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/payments-service-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
