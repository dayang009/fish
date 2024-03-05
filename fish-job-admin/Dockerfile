FROM alibabadragonwell/dragonwell:8-anolis

MAINTAINER dayang <dayangtop@163.com>

WORKDIR /fish-job-admin

ARG JAR_FILE=target/fish-job-admin.jar

COPY ${JAR_FILE} app.jar

EXPOSE 36903

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m"

CMD sleep 5; java $JAVA_OPTS -jar app.jar
