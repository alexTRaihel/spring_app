FROM openjdk:11-jdk
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} profile-service.jar
ENTRYPOINT ["java","-jar","/profile-service.jar"]