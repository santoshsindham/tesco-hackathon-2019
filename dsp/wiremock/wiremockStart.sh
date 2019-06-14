#!/bin/sh

JAVA_OPTS="\
 $JAVA_OPTS \
 -XX:+UseG1GC \
 -Xms512m \
 -Xmx1536m \
 -XX:MaxMetaspaceSize=256m"

echo "staring wiremock server.."
java ${JAVA_OPTS} -jar /wiremock/wiremock-jre8-standalone.jar --port 8085 &

PID=$!
echo ${PID} > cdp.pid
echo "starting docker container with wiremock process id ${PID}"
wait ${PID}
