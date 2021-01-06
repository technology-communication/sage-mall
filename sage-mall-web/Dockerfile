FROM openjdk:8-jdk-alpine
ADD target/sage-mall-web-1.0-SNAPSHOT.jar sage-mall-web.jar
EXPOSE 8088
ENTRYPOINT  ["java","-jar","/sage-mall-web.jar"]