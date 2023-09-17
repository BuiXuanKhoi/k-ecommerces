
# Specifies build tool
FROM maven:3.9.3-eclipse-temurin-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the dependencies for download

COPY pom.xml .

# Run command download dependencies

RUN mvn dependency:go-offline -B

# Copy source code

COPY src src

# Package the whole project into jar file
RUN mvn package -Dskiptests

# Choose base image for java version
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app


# Copy the compiled JAR file from the build stage
COPY --from=build /app/target/data-connector.jar data-connector.jar

# Expose port for running the application
EXPOSE 8091
EXPOSE 9200

# Command start application

CMD ["java", "jar", "data-connector.jar"]
