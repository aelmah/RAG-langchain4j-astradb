FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/pdf-assistant-langchain4j-astradb.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]