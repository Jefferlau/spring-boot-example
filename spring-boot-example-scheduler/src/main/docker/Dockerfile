FROM java:8
MAINTAINER Jeffer Lau
VOLUME /tmp
ADD spring-boot-example-package.jar app.jar
COPY application.yml application.yml
COPY app.conf app.conf
RUN sh -c 'touch /app.jar'
RUN sh -c 'touch /application.yml'
RUN sh -c 'touch /app.conf'
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "/app.jar" ]
