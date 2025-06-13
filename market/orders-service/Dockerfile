FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/orders-service-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
