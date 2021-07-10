FROM openjdk:11
MAINTAINER baeldung.com
COPY build/libs/initializr-0.0.1.jar initializr-0.0.1.jar
COPY templateFile templateFile
COPY template template
ENTRYPOINT ["java","-jar","/initializr-0.0.1.jar"]
EXPOSE 8091