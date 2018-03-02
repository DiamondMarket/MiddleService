FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/middleService-0.0.1-SNAPSHOT.jar /middleService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/middleService-0.0.1-SNAPSHOT.jar"]
 
