FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y curl

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]