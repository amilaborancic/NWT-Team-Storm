FROM openjdk:13
ADD target/catalogue-docker.jar catalogue-docker.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "catalogue-docker.jar"]
