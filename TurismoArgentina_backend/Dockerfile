FROM maven:3.8.6-eclipse-temurin-17

RUN mkdir -p /TurismoArgentina/backend

RUN mkdir -p /images

COPY ./target/TurismoArgentina_backend-0.1.jar /TurismoArgentina/backend

CMD ["java", "-jar", "/TurismoArgentina/backend/TurismoArgentina_backend-0.1.jar"]