FROM alibabadragonwell/dragonwell:8-anolis

MAINTAINER dayang <dayangtop@163.com>

WORKDIR /fish

ARG GROUP_ID=${GROUP_ID}
ARG ARTIFACT_ID=${ARTIFACT_ID}
ARG VERSION=${VERSION}
ARG CI_PIPELINE_ID=${CI_PIPELINE_ID}
ARG CI_JOB_ID=${CI_JOB_ID}
ARG JAVA_OPTS=${JAVA_OPTS}

RUN printf "GROUP_ID: %s\n" "$GROUP_ID" \
    && printf "ARTIFACT_ID: %s\n" "$ARTIFACT_ID" \
    && printf "VERSION: %s\n" "$VERSION" \
    && printf "CI_PIPELINE_ID: %s\n" "$CI_PIPELINE_ID" \
    && printf "CI_JOB_ID: %s\n" "$CI_JOB_ID" \
    && printf "JAVA_OPTS: %s\n" "$JAVA_OPTS" \
    && printf "APP_ARGS: %s\n" "$APP_ARGS"

COPY target/$ARTIFACT_ID-$VERSION.jar app.jar

EXPOSE 8761

ENV TZ=Asia/Shanghai \
    LD_LIBRARY_PATH=/usr/local/lib \
    LANG=C.UTF-8 \
    GROUP_ID=${GROUP_ID} \
    ARTIFACT_ID=${ARTIFACT_ID} \
    VERSION=${VERSION} \
    CI_PIPELINE_ID=${CI_PIPELINE_ID} \
    CI_JOB_ID=${CI_JOB_ID} \
    JAVA_OPTS=${JAVA_OPTS} \
    APP_ARGS=${APP_ARGS}

CMD java $JAVA_OPTS -jar app.jar $APP_ARGS
