FROM amazoncorretto:17

WORKDIR /tmp

COPY ./target/Group4-0.1.0.9.jar /tmp

LABEL authors="Cotximiahou"
LABEL version="0.1.0.8"
LABEL description="World Population Reporting System - Spring Boot"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Group4-0.1.0.9.jar"]
