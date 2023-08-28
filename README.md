## wangBook
### Docker部署流程
* 进入docker目录
```shell
cd docker
```
* 执行docker-compose.yml脚本
```shell
docker compose up -d
```
* 连接mysql
```shell
# 使用远程工具连接mysql，端口4406
# 在mysql下创建一个wang_book数据库
# 将doc目录下的数据库脚本在mysql中执行
```
* 访问81端口，用户端；访问82端口，管理端
### 
