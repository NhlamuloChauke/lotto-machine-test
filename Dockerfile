FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} machineTest.jar

ENTRYPOINT ["java", "-jar", "/machineTest.jar"]