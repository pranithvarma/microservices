FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y curl

COPY target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","eurekaserver-0.0.1-SNAPSHOT.jar"]