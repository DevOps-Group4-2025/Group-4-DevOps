FROM openjdk:latest
COPY ./target/Group4-0.1.0.1.jar /tmp
LABEL authors="Cotximiahou"
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Group4-0.1.0.1.jar"]