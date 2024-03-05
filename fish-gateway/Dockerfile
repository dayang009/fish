FROM alibabadragonwell/dragonwell:8-anolis

MAINTAINER dayang <dayangtop@163.com>

WORKDIR /fish-gateway

ARG JAR_FILE=target/fish-gateway.jar

COPY ${JAR_FILE} app.jar

EXPOSE 36901

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m"

CMD sleep 3; java $JAVA_OPTS -jar app.jar
