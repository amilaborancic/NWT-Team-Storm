FROM openjdk:13-jdk-alpine
ADD target/events-docker.jar events-docker.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "events-docker.jar"]
