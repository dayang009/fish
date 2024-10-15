FROM maven:3.9.9-amazoncorretto-17

WORKDIR /fish-gateway

ARG JAR_FILE=target/fish-gateway.jar

COPY ${JAR_FILE} app.jar

EXPOSE 36901

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms256m -Xmx512m"

CMD sleep 5; java $JAVA_OPTS -jar app.jar
