FROM openjdk:8
ADD target/technical-test.jar technical-test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "technical-test.jar"]