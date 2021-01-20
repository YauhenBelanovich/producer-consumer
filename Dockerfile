FROM maven:3.6.3-jdk-11-slim as DEPS

WORKDIR /opt/app
COPY consumer-service-module/pom.xml consumer-service-module/pom.xml
COPY producer-service-module/pom.xml producer-service-module/pom.xml
COPY consumer-spring-boot-module/pom.xml consumer-spring-boot-module/pom.xml
COPY producer-spring-boot-module/pom.xml producer-spring-boot-module/pom.xml
COPY pom.xml .
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

FROM maven:3.6.3-jdk-11-slim as BUILDER
WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY consumer-service-module/src /opt/app/consumer-service-module/src
COPY producer-service-module/src /opt/app/producer-service-module/src
COPY consumer-spring-boot-module/src /opt/app/consumer-spring-boot-module/src
COPY producer-spring-boot-module/src /opt/app/producer-spring-boot-module/src

RUN mvn -B -e clean install -DskipTests=true

FROM openjdk:11
WORKDIR /opt/app
COPY --from=builder /opt/app/consumer-spring-boot-module/target/consumer-spring-boot-module-0.0.1-SNAPSHOT.jar .
EXPOSE 8081

COPY --from=builder /opt/app/producer-spring-boot-module/target/producer-spring-boot-module-0.0.1-SNAPSHOT.jar .
EXPOSE 8082

LABEL maintainer="yauhen2012@gmail.com"
