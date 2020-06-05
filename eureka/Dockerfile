FROM openjdk:13-jdk-alpine
ADD target/eureka-docker.jar eureka-docker.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-docker.jar"]
