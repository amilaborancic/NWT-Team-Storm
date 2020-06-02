FROM openjdk:13
ADD target/comicbook-docker.jar comicbook-docker.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "comicbook-docker.jar"]