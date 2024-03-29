FROM harbor.olavoice.com/library/oracle/serverjre:8

ARG JAR_FILE
ARG WORK_PATH="/opt/demo"

ENV JAVA_OPTS="" \
    JAR_FILE=${JAR_FILE}

#设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone

COPY target/$JAR_FILE $WORK_PATH/

WORKDIR $WORK_PATH

ENTRYPOINT exec java $JAVA_OPTS -jar $JAR_FILE