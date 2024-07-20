FROM alibabadragonwell/dragonwell:8-anolis

WORKDIR /fish-register

ARG JAR_FILE=target/fish-register.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8761

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms256m -Xmx512m"

CMD sleep 5; java $JAVA_OPTS -jar app.jar
