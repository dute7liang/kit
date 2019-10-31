#! /bin/bash
# rem 设置编码
JAVA_OPTS='-Dfile.encoding=utf-8'

# 设置JVM内存属性
JAVA_OPTS=${JAVA_OPTS}' -Xms2G -Xmx2G'

# GC信息
JAVA_OPTS=${JAVA_OPTS}' -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps \
-XX:+PrintHeapAtGC -Xloggc:../logs/gc.log'

JAVA_OPTS=${JAVA_OPTS}' -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./'

# jmx远程监控
JAVA_OPTS=${JAVA_OPTS}' -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1122 \
-Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false \
-Djava.rmi.server.hostname=127.0.0.1'

# 版本号
version='0.0.1'

MAVEN_OPTS='-Dspring.devtools.restart.enabled=false'

JAR_PATH='..\web-'${version}'.jar'

EXECUTABLE='java '${JAVA_OPTS}' '${MAVEN_OPTS}' -jar '${JAR_PATH}' --spring.profiles.active=release'

nohup ${EXECUTABLE} &
exit