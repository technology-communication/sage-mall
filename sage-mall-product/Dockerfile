FROM openjdk:8-jdk-alpine
ADD target/sage-mall-product-1.0-SNAPSHOT.jar sage-mall-product.jar
EXPOSE 20880
ENTRYPOINT  ["java","-jar","/sage-mall-product.jar"]