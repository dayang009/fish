FROM openjdk:8-jre

MAINTAINER dayang <dayangtop@163.com>

RUN mkdir /app

COPY fish-register.jar /app/app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]

EXPOSE 8761