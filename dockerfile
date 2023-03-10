FROM openjdk:17-oracle
COPY target/*.jar ms-loans.jar
ENTRYPOINT ["java", "-jar", "/ms-loans.jar"]
EXPOSE 8081