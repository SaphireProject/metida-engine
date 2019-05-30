FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENV RUNNER_URL=localhost
ENTRYPOINT ["java","-jar","/app.jar"]