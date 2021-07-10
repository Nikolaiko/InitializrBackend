FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY build/libs/initializr-0.0.1.jar initializr-0.0.1.jar
ENTRYPOINT ["java","-jar","/initializr-0.0.1.jar"]
EXPOSE 8091