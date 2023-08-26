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

# 工作目录app
WORKDIR /app

# 从第一个阶段拷贝jar过来
COPY --from=build /app/target/after-end-1.0.jar .

CMD [ "java" ,"-jar" , "after-end-1.0.jar"]
