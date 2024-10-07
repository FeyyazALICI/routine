# Step 1: Build the Maven project
FROM maven:3.9.4 AS build
WORKDIR /app

# Copy pom.xml and resolve dependencies to leverage Docker cache
COPY pom.xml /app
RUN mvn dependency:resolve

# Copy the rest of the application source code
COPY src /app/src

# Clean and package the application
RUN mvn clean package -DskipTests

# Step 2: Use OpenJDK to run the Spring Boot app
FROM openjdk:17
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9000
CMD ["java", "-jar", "app.jar"]