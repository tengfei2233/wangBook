# 分阶段构建二，运行jar包
FROM openjdk:8u212-jre-alpine3.9

# 指定容器元标签
LABEL maintainer="tengfei"
LABEL version="1.0.0"
LABEL description="wangBook的后端"

# 工作目录app
WORKDIR /app

# 从第一个阶段拷贝jar过来
COPY after-end.jar .

CMD [ "java" ,"-jar" , "after-end.jar"]