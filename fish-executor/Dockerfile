FROM alibabadragonwell/dragonwell:8-anolis

WORKDIR /fish-executor

ARG JAR_FILE=target/fish-executor.jar

COPY ${JAR_FILE} app.jar

EXPOSE 36905

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m"

CMD sleep 3; java $JAVA_OPTS -jar app.jar
