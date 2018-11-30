#java 运行时环境
FROM java:8
MAINTAINER heiden deng<heiden_deng@163.com>
ENV TZ=Asia/Shanghai
RUN apt-get install tzdata
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p /app 
#需要将项目打包后jar包路径 
ADD ./target/*.jar  /app
WORKDIR /app
EXPOSE 443
CMD ["java","-jar","cloud-platform-demo-1.0.0-SNAPSHOT.jar"]