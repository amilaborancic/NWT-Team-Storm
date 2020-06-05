FROM openjdk:13-jdk-alpine
ADD target/user-docker.jar user-docker.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "user-docker.jar"]