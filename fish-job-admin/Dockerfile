FROM alibabadragonwell/dragonwell:8-anolis

MAINTAINER dayang <dayangtop@163.com>

WORKDIR /fish-job-admin

ARG JAR_FILE=target/fish-job-admin.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8083

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD sleep 30; java $JAVA_OPTS -jar app.jar
