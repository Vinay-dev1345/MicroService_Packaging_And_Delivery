FROM openjdk:11
EXPOSE 9003
ADD target/packagingAndDelivery-0.0.1-SNAPSHOT.jar packagingAndDelivery-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","/packagingAndDelivery-0.0.1-SNAPSHOT.jar"]