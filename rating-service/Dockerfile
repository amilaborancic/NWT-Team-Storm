FROM openjdk:13-jdk-alpine
ADD target/rating-service.jar rating-service.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "rating-service.jar"]
