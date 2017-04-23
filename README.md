Spring Boot 的一些使用记录
---

# Filter

当 Filter 无序时使用 @ServletComponentScan 扫描即可。
当 Filter 需要排序时，见 WebConfig 中的 FilterRegistrationBean 配置，
此时 @WebFilter 不再生效， name 和 urlPatterns 都在 FilterRegistrationBean 里配置，order 越小越靠前。

# Interceptor

# Listener

# Servlet

# ExceptionHandler

# Scheduled

# 普通类调用 Spring bean 对象。

注意使用时以下两种方式任选其一：
1. 此类加上 @Component 注解并需要放到可以被被扫描的目录。
2. 在任意 @Configuration 定义 @Bean public SpringUtil springUtil(){return new SpringUtil();}
3. 在任意 @Configuration 类上注解 @Import(value={SpringUtil.class})

# Docker


```bash
mkdir src/main/docker
tee src/main/docker/Dockerfile <<-'EOF'
FROM java:8
MAINTAINER Jeffer Lau
VOLUME /tmp
ADD spring-boot-example-package.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
EOF
# OR
tee src/main/docker/Dockerfile <<-'EOF'
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
EOF

mvn clean package docker:build -e -DskipTests 

docker run -p 8082:8082 -d --name spring_boot_example -t jefferlau/spring-boot-example
docker ps
docker stop spring_boot_example
docker start spring_boot_example

docker exec -it spring_boot_example /bin/bash

```