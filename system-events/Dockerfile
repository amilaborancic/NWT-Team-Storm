FROM openjdk:13
ADD target/events-docker.jar events-docker.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "events-docker.jar"]
