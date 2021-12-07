FROM openjdk:11
RUN useradd userbootcamp
USER userbootcamp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]