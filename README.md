Spring Boot 的一些使用记录
---

# Filter
components 包下

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

# Swagger

## 常用注解

- ``@Api``：用在类上，说明该类的作用
  - value：url 的路径值
  - tags：如果设置这个值、value 的值会被覆盖
  - description：对 api 资源的描述
  - basePath：基本路径可以不配置
  - position：如果配置多个 Api 想改变显示的顺序位置
  - produces：For example, "application/json, application/xml"
  - consumes：For example, "application/json, application/xml"
  - protocols：Possible values: http, https, ws, wss.
  - authorizations：高级特性认证时配置
  - hidden：配置为 true 将在文档中隐藏
- ``@ApiOperation``：用在方法上，说明方法的作用
  - value：url的路径值
  - tags：如果设置这个值、value 的值会被覆盖
  - notes：对api资源的描述
  - response：返回的对象
  - responseContainer：这些对象是有效的 "List", "Set" or "Map".，其他无效
  - responseReference：可以不配置
  - httpMethod：可以接受 "GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS" and "PATCH"
  - position：如果配置多个 Api 想改变显示的顺序位置
  - produces：For example, "application/json, application/xml"
  - consumes：For example, "application/json, application/xml"
  - protocols：值 http, https, ws, wss.
  - authorizations：高级特性认证时配置
  - hidden：配置为 true 将在文档中隐藏
  - code：http的状态码 默认 200
  - extensions：扩展属性
- ``@ApiParam`` 请求属性
  - name：属性名称
  - value：属性值
  - defaultValue：默认属性值
  - allowableValues：可以不配置
  - required：是否属性必填
  - access：不过多描述
  - allowMultiple：默认为 false
  - hidden：隐藏该属性
  - example：举例子
  - examples
- ``@ApiImplicitParams``：用在方法上包含一组参数说明
- ``@ApiImplicitParam``：用在``@ApiImplicitParams``注解中，指定一个请求参数的各个方面
  - paramType：参数放在哪个地方
    - header --> 请求参数的获取：``@RequestHeader``
    - query --> 请求参数的获取：``@RequestParam`` 或者 ``@ModelAttribute``
    - path（用于 RESTful 接口）--> 请求参数的获取：``@PathVariable``
    - body
    - form
  - name：参数名
  - dataType：参数类型
  - required：参数是否必须传
  - value：参数的意思
  - defaultValue：参数的默认值
- ``@ApiResponses``：用于表示一组响应集
  - value：多个``@ApiResponse``配置
- ``@ApiResponse``：用在``@ApiResponses``中，一般用于表达一个错误的响应信息
  - code：http的状态码
  - message：描述
  - response：默认响应类 Void
  - reference：参考``@ApiOperation``中配置
  - responseHeaders：参考``@ResponseHeader``属性配置说明	
  - responseContainer：参考``@ApiOperation``中配置
- ``@ResponseHeader``
  - name：响应头名称	
  - description：头描述	
  - response：默认响应类 Void	
  - responseContainer：参考``@ApiOperation``中配置
- ``@ApiModel``：描述一个 Model 的信息（这种一般用在 POST 创建的时候，使用``@RequestBody``这样的场景，请求参数无法使用``@ApiImplicitParam``注解进行描述的时候）
  - value：默认为类的名称	
  - description：对该类的描述	
  - parent：可以不配置	
  - discriminator：可以不配置	
  - subTypes：可以不配置	
  - reference：引用配置可以不考虑
- ``@ApiModelProperty``：描述一个 Model 的属性
  - value：属性描述	
  - name：如果配置覆盖属性名称	
  - allowableValues：参考``@ApiParam``配置项项	
  - access：可以不配置	
  - notes：没有使用	
  - dataType：数据类型	
  - required：参考``@ApiParam``配置项项	
  - position：参考``@ApiOperation``配置项	
  - hidden：参考``@ApiOperation``配置项	
  - example：参考``@ApiParam``配置项项	
  - readOnly		
  - reference：参考``@ApiParam``配置项项

[注解说明文档](https://github.com/swagger-api/swagger-core/wiki/Annotations#apimodel)

# Alibaba Druid

[参考一](https://github.com/alibaba/druid/wiki/%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)
[参考二](http://www.debugrun.com/a/48WqhXI.html)
[参考三](http://www.bysocket.com/?p=1712)
[参考四](https://ln0491.github.io/2017/03/06/spring-boot%E5%85%A5%E9%97%A8%E4%B8%83%E9%85%8D%E7%BD%AEalibaba-druid%E6%95%B0%E6%8D%AE%E6%BA%90/)
