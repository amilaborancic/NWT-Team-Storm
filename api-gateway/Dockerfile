FROM openjdk:13
ADD target/gateway-docker.jar gateway-docker.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "gateway-docker.jar"]
