# 构建front-clien，Vue前端前台代码
# 分阶段构建一，安装依赖，打包代码
FROM node:14.21.3-alpine3.16 as build

# 设置工作目录
WORKDIR /app

# 拷贝代码
COPY ./src ./src
COPY ./public ./public
COPY package.json .env.production jsconfig.json vue.config.js babel.config.js ./

# 下载依赖，打包
RUN npm install --registry=https://registry.npm.taobao.org/ && \
    npm run build

# 分阶段构建二，将打包好的代码带nginx上运行
FROM nginx:1.22.1-alpine-slim

# 指定容器元标签
LABEL maintainer="tengfei"
LABEL version="1.0.0"
LABEL description="wangBook的用户端前端"

#拷贝打包好的文件到文件夹
COPY --from=build /app/dist /usr/share/nginx/html

# 将本地nginx.conf拷贝上去，可选，需要自定义nginx配置可选
# COPY nginx.conf /etc/nginx/nginx.conf

# 暴露端口
EXPOSE 80

# 启动nginx，这个的daemon off是停止守护线程模型，就是不在后台运行，要不然容器会停止
CMD ["nginx", "-g","daemon off;"]