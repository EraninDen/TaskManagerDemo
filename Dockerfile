FROM openjdk:17-alpine
WORKDIR /app
COPY target/demo.jar .
ENTRYPOINT ["java", "-jar", "demo.jar"]
EXPOSE 8080