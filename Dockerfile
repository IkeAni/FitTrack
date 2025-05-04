FROM openjdk:17-jdk-slim

# Kopioi JAR
COPY target/fittrack-0.0.1-SNAPSHOT.jar app.jar

# Aja sovellus
ENTRYPOINT ["java", "-jar", "/app.jar"]