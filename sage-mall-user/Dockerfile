FROM openjdk:8-jdk-alpine
ADD target/sage-mall-user-1.0-SNAPSHOT.jar sage-mall-user.jar
EXPOSE 20880
ENTRYPOINT  ["java","-jar","/sage-mall-user.jar"]