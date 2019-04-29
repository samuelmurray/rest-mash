#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src ./src
COPY pom.xml ./
RUN mvn -f ./pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build ./target/gs-rest-service-0.2-alpha.jar /usr/local/lib/rest-mash.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/rest-mash.jar"]