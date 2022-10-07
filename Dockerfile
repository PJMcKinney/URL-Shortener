FROM maven:3.8.6 AS builder

ADD ./src src/
ADD pom.xml pom.xml

RUN mvn clean package

FROM maven:3.8.6

COPY --from=builder target/urlShortener-0.0.1-SNAPSHOT.jar urlShortener-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "urlShortener-0.0.1-SNAPSHOT.jar"]