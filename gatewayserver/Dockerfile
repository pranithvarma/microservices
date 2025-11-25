FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y curl

COPY target/gatewayserver-0.0.1-SNAPSHOT.jar gatewayserver-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","gatewayserver-0.0.1-SNAPSHOT.jar"]