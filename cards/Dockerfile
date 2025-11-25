FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y curl

COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","cards-0.0.1-SNAPSHOT.jar"]