@echo off
title=CollectStorage
CHCP 65001

rem 设置编码
set JAVA_OPTS=-Dfile.encoding=utf-8

rem 设置JVM内存属性
set JAVA_OPTS=%JAVA_OPTS% -Xms2G -Xmx2G

rem GC信息
set JAVA_OPTS=%JAVA_OPTS% -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps ^
-XX:+PrintHeapAtGC -Xloggc:../logs/gc.log

set JAVA_OPTS=%JAVA_OPTS% -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./

rem jmx远程监控
set JAVA_OPTS=%JAVA_OPTS% -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1122 ^
-Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false ^
-Djava.rmi.server.hostname=127.0.0.1

rem 版本号
set version=0.0.1

set MAVEN_OPTS=-Dspring.devtools.restart.enabled=false

java %JAVA_OPTS% %MAVEN_OPTS% -jar ..\web-%version%.jar --spring.profiles.active=release
@pause