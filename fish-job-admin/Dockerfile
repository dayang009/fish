FROM alibabadragonwell/dragonwell:8-anolis

WORKDIR /fish-job-admin

ARG JAR_FILE=target/fish-job-admin.jar

COPY ${JAR_FILE} app.jar

EXPOSE 36903

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms256m -Xmx512m"

CMD sleep 5; java $JAVA_OPTS -jar app.jar
