#!/bin/bash

# 定义要创建的目录列表
directories=(
    "/data/wangbook/nginx-client/conf" "/data/wangbook/nginx-manage/conf" \
    "/data/wangbook/mysql/log" "/data/wangbook/mysql/data" "/data/wangbook/mysql/conf" \
    "/data/wangbook/redis/data" "/data/wangbook/redis/conf"
    )

# 遍历目录列表并创建目录
for dir in "${directories[@]}"
do
  mkdir -p "$dir"
  echo "创建目录成功: $dir"
done

# 创建nginx，conf文件，并写入内容
clientconf="/data/wangbook/nginx-client/conf/nginx.conf"
touch "$clientconf"
cat > "$clientconf" <<EOF

user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    gzip  on;

    server {
        listen       80;
        server_name  localhost;

        location / {
            root html;
            index  index.html index.htm;
            try_files $uri $uri/ /index.html;
        }

        # nginx代理
        location /api {
                # 将/api替换成空，$1表示(.*)
                rewrite  ^/api/(.*)$ /uesr/$1 break;
                # 连接到后端docker容器
                proxy_pass http://after-end:8080;
        }
    }

}

EOF

echo "创建客户端nginx配置成功"


# 创建nginx，conf文件，并写入内容
manageconf="/data/wangbook/nginx-manage/conf/nginx.conf"
touch "$manageconf"
cat > "$manageconf" <<EOF

user  nginx;
worker_processes  auto;

error_log  /var/log/nginx/error.log notice;
pid        /var/run/nginx.pid;


events {
    worker_connections  1024;
}


http {
    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    keepalive_timeout  65;

    gzip  on;

    server {
        listen       80;
        server_name  localhost;

        location / {
            root html;
            index  index.html index.htm;
            try_files $uri $uri/ /index.html;
        }

        # nginx代理
        location /api {
                # 将/api替换成空，$1表示(.*)
                rewrite  ^/api/(.*)$ /manage/$1 break;
                # 连接到后端docker容器
                proxy_pass http://after-end:8080;
        }
    }

}

EOF
echo "创建管理端nginx配置成功"


# 创建配置文件
mysqlconf="/data/wangbook/mysql/conf/my.cnf"
touch "$mysqlconf"
cat > "$mysqlconf" << EOF

[mysqld]
init-connect="SET collation_connection=utf8mb4_0900_ai_ci"
init_connect="SET NAMES utf8mb4"
skip-character-set-client-handshake

EOF

echo "创建mysql配置文件成功"