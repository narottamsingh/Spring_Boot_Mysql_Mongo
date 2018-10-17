FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD /target/docker-0.0.1.jar docker-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/docker-0.0.1.jar"]