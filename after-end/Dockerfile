# 构建after_end，SpringBoot后端代码
# 分阶段构建一，编译打包代码
# 使用官方仓库的mavan3.9镜像
FROM maven:3.9.3-eclipse-temurin-8-alpine as build

# 工作目录/app
WORKDIR /app

# 拷贝文件进入工作目录
COPY ./src ./src
COPY  ./pom.xml ./

# 执行maven打包构建
RUN mvn clean package -DskipTests


# 分阶段构建二，运行jar包
FROM openjdk:8u212-jre-alpine3.9

# 指定容器元标签
LABEL maintainer="tengfei"
LABEL version="1.0.0"
LABEL description="wangBook的后端"

# 工作目录app
WORKDIR /app

# 因为8u212-jre-alpine3.9该linux缺少字体，以下为安装字体，如果更改linux，请对应更改
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories && \
    apk update && \
    apk add fontconfig && \
    apk add --update ttf-dejavu && fc-cache --force

# 从第一个阶段拷贝jar过来
COPY --from=build /app/target/after-end-1.0.jar .

CMD [ "java" ,"-jar" , "after-end-1.0.jar"]

