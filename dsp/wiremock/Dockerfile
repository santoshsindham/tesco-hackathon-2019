FROM java:8-jre-alpine
ENV WIREMOCK_VERSION 2.18.0
RUN mkdir -p /wiremock/
RUN wget http://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/$WIREMOCK_VERSION/wiremock-standalone-$WIREMOCK_VERSION.jar \
 -O /wiremock/wiremock-jre8-standalone.jar
COPY wiremock /wiremock/
RUN chmod +x /wiremock/wiremockStart.sh
RUN ls -la /wiremock/*
WORKDIR /wiremock
EXPOSE 8085
ENTRYPOINT ["/bin/sh", "-c", "/wiremock/wiremockStart.sh", "--"]
