# Build stage
FROM gradle:7.6.1-jdk17 AS build

WORKDIR /home/gradle/springyou
COPY --chown=gradle:gradle . .

RUN gradle build --no-daemon

# Create user
# RUN useradd -ms /bin/bash spring

# Execution stage
FROM openjdk:17-jdk-slim

WORKDIR /app/
COPY --from=build /home/gradle/springyou/build/libs/springyou-0.0.1-SNAPSHOT.jar app.jar

# Change ownership (optional)
# RUN chown spring:spring /app/app.jar

# Switch to non-root user
# USER spring

# Directory for storing static files
RUN mkdir /static
# Root directory for FileSystemResource
RUN mkdir /app/static
# Symbolic link from the FileSystemResource directory to the static files directory
RUN ln -s /static /app/static/link

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
