FROM openjdk:17-jdk-slim

# Kopioi rakennettu JAR
COPY target/fittrack.jar app.jar

# Aja sovellus
ENTRYPOINT ["java", "-jar", "/app.jar"]