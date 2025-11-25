FROM eclipse-temurin:17-jdk-jammy
RUN apt-get update && apt-get install -y curl

COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","loans-0.0.1-SNAPSHOT.jar"]