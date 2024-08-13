FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/ExpenseTrackerApplication.jar ExpenseTrackerApplication.jar

ENTRYPOINT ["java", "-jar", "ExpenseTrackerApplication.jar"]