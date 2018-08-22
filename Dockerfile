FROM openjdk:8u151-jre-alpine

RUN apk add --update openssl && \
    rm -rf /var/cache/apk/*

COPY target/hazelcast-cloud-sample-0.0.1-SNAPSHOT.jar /
CMD java -jar /hazelcast-cloud-sample-0.0.1-SNAPSHOT.jar