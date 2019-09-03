@echo off
java -Dspring.devtools.restart.enabled=false -Dfile.encoding=utf-8 -jar ..\web-0.0.1.jar --spring.profiles.active=release
@pause