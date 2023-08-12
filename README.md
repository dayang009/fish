# 微服务项目

Hello World!!!



## 依赖版本对比

### 后端

- 更新日期：2023-08-08

| 依赖                           | 本项目版本  | 新版                                                         | 说明                                                         |
| ------------------------------ | ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| spring-boot                    | v2.7.14     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2.&metadataUrl=https://s01.oss.sonatype.org/content/repositories/releases/org/springframework/boot/spring-boot-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| spring-cloud                   | v2021.0.8   | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2021&metadataUrl=https://s01.oss.sonatype.org/content/repositories/releases/org/springframework/cloud/spring-cloud-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| spring-cloud-alibaba           | v2021.0.5.0 | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2021.0&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/alibaba/cloud/spring-cloud-alibaba-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x，[Spring Cloud Alibaba](https://github.com/alibaba/spring-cloud-alibaba/blob/2022.x/README-zh.md) |
| spring-boot-admin              | v2.7.10     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2.&metadataUrl=https://oss.sonatype.org/content/repositories/releases/de/codecentric/spring-boot-admin-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| spring-authorization-server    | v0.4.2      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=0.&metadataUrl=https://s01.oss.sonatype.org/content/repositories/releases/org/springframework/security/spring-security-oauth2-authorization-server/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| mybatis                        | v2.3.1      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2.&metadataUrl=https://oss.sonatype.org/content/repositories/releases/org/mybatis/spring/boot/mybatis-spring-boot-starter/maven-metadata.xml) | 限制 Spring Boot 2.x，指 mybatis-spring-boot-starter         |
| mybatis-plus                   | v3.5.3.2    | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/baomidou/mybatis-plus-boot-starter/maven-metadata.xml) |                                                              |
| pagehelper-spring-boot-starter | v1.4.7      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/github/pagehelper/pagehelper-spring-boot-starter/maven-metadata.xml) | MyBatis 推荐分页插件                                         |
| dynamic-datasource             | v3.6.1      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=3.&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/baomidou/dynamic-datasource-spring-boot-starter/maven-metadata.xml) | 指 dynamic-datasource-spring-boot-starter                    |
| druid-spring-boot-starter      | v1.2.18     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/alibaba/druid-spring-boot-starter/maven-metadata.xml) | 德鲁伊链接池                                                 |
| knife4j                        | v4.3.0      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/github/xiaoymin/knife4j-dependencies/maven-metadata.xml) | Knife4j是一个集Swagger2和OpenAPI3为一体的增强解决方案        |
| springdoc                      | 1.7.0       | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/org/springdoc/springdoc-openapi-ui/maven-metadata.xml) | 用于生成 API doc，支持从 javadoc 中获取字段注释              |
| guava                          | v32.1.2-jre | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/google/guava/guava/maven-metadata.xml) |                                                              |
| fastjson                       |             | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/alibaba/fastjson/maven-metadata.xml) | 使用的是不带英文后缀的版本                                   |
| hutool                         | v5.8.21     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/cn/hutool/hutool-all/maven-metadata.xml) | Hutool 是一个小而全的 Java 工具类库                          |



## Note

yml 和 properties 配置文件格式在线转换网址：https://www.toyaml.com

快速搜索依赖网址：https://mvn.coderead.cn/



## Userful SQL

``` sql
-- 将已有的序列和主键ID关联的SQL语句
alter table public.t_user
    alter column id set default nextval('public.t_user_id_seq')
```





## 删除maven仓库中的无用文件



delRepo.sh

``` bash
echo search ing ...
find . -name "*lastUpdated" | xargs rm -rf
find . -name "_remote.repositories" | xargs rm -rf
echo The End.
```



delRepo.cmd

``` bash
set REPOSITORY_PATH=C:\repository
rem Searching ...
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*_remote.repositories*"') do (
    del /s /q %%i
)
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
    del /s /q %%i
)
rem Delete Success !!!
pause 
```



下载Jar包导入离线环境，在pom文件所在的路径执行下面命令

``` sh
mvn -f pom.xml dependency:copy-dependencies
```

pom.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>demo</artifactId>
    <version>1.0</version>

    <properties>
        <java.version>1.8</java.version>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.0.32</version>
        </dependency>


    </dependencies>

</project>
```



## 运行项目提示端口被占用

``` bash
## 根据端口找进程ID
netstat -ano | findstr "8182"

TCP    0.0.0.0:8182           0.0.0.0:0              LISTENING       18688
TCP    [::]:8182              [::]:0                 LISTENING       18688

## 杀掉对应进程
taskkill /t /f /im 18688

成功: 已终止 PID 22004 (属于 PID 18688 子进程)的进程。
成功: 已终止 PID 18688 (属于 PID 15592 子进程)的进程。

## 查看对应应用名称
tasklist | findstr "PID号"
tasklist | findstr "18688"

java.exe                     18688 Console                    1    307244 K


```







## PageHelper简单使用教程

只有紧跟在`PageHelper.startPage`方法后的**第一个**Mybatis的**查询（Select）**方法会被分页。

``` java
//第一种，RowBounds方式的调用
List<User> list = sqlSession.selectList("x.y.selectIf", null, new RowBounds(0, 10));

//第二种，Mapper接口方式的调用，推荐这种使用方式。
PageHelper.startPage(1, 10);
List<User> list = userMapper.selectIf(1);

//第三种，Mapper接口方式的调用，推荐这种使用方式。
PageHelper.offsetPage(1, 10);
List<User> list = userMapper.selectIf(1);

//第四种，参数方法调用
//存在以下 Mapper 接口方法，你不需要在 xml 处理后两个参数
public interface CountryMapper {
    List<User> selectByPageNumSize(
            @Param("user") User user,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize);
}
//配置supportMethodsArguments=true
//在代码中直接调用：
List<User> list = userMapper.selectByPageNumSize(user, 1, 10);

//第五种，参数对象
//如果 pageNum 和 pageSize 存在于 User 对象中，只要参数有值，也会被分页
//有如下 User 对象
public class User {
    //其他fields
    //下面两个参数名和 params 配置的名字一致
    private Integer pageNum;
    private Integer pageSize;
}
//存在以下 Mapper 接口方法，你不需要在 xml 处理后两个参数
public interface CountryMapper {
    List<User> selectByPageNumSize(User user);
}
//当 user 中的 pageNum!= null && pageSize!= null 时，会自动分页
List<User> list = userMapper.selectByPageNumSize(user);

//第六种，ISelect 接口方式
//jdk6,7用法，创建接口
Page<User> page = PageHelper.startPage(1, 10).doSelectPage(new ISelect() {
    @Override
    public void doSelect() {
        userMapper.selectGroupBy();
    }
});
//jdk8 lambda用法
Page<User> page = PageHelper.startPage(1, 10).doSelectPage(()-> userMapper.selectGroupBy());

//也可以直接返回PageInfo，注意doSelectPageInfo方法和doSelectPage
pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(new ISelect() {
    @Override
    public void doSelect() {
        userMapper.selectGroupBy();
    }
});
//对应的lambda用法
pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> userMapper.selectGroupBy());

//count查询，返回一个查询语句的count数
long total = PageHelper.count(new ISelect() {
    @Override
    public void doSelect() {
        userMapper.selectLike(user);
    }
});
//lambda
        total=PageHelper.count(()->userMapper.selectLike(user));
```



## Navicat16下载地址

http://download.navicat.com/download/navicat161_premium_cs_x64.exe

reset.bat

``` bash
@echo off

echo Delete HKEY_CURRENT_USER\Software\PremiumSoft\NavicatPremium\Registration[version and language]
for /f %%i in ('"REG QUERY "HKEY_CURRENT_USER\Software\PremiumSoft\NavicatPremium" /s | findstr /L Registration"') do (
    reg delete %%i /va /f
)
echo.

echo Delete Info folder under HKEY_CURRENT_USER\Software\Classes\CLSID
for /f %%i in ('"REG QUERY "HKEY_CURRENT_USER\Software\Classes\CLSID" /s | findstr /E Info"') do (
    reg delete %%i /va /f
)
echo.

echo Finish

pause
```



## NodeJs Config

官网网址：https://nodejs.org/zh-cn

推荐使用16版本：https://nodejs.org/dist/latest-v16.x/

选择后缀为`-win-x64.zip`,例如：`/node-v16.xx.x-win-x64.zip`

配置`Path`环境变量(根据实际情况选择安装位置的路径):`D:\Develop\node-v16.20.1-win-x64`

配置国内淘宝镜像源

按照如下目录结构新建文件，注意：`npmrc`是文件，没有后缀。

`cache`对应的值是`nodejs`实际的存放路径，注意修改，路径不要有中文和空格

```txt
node-v16.20.1-win-x64
├─etc
|  └─npmrc
├─node_modules
├─npm-cache

```



npmrc

```
registry=https://registry.npmmirror.com/
cache=D:\\Develop\\node-v16.20.1-win-x64\\npm-cache
```

```
## 关闭安全提示
set-ExecutionPolicy RemoteSigned

## 查看详细的node配置展示列表，可查看上述配置是否生效
npm config ls -ls
```





## Docker 搭建 FTP 服务器



``` ini
docker run  \
-d \
-p "20":"20" \
-p "21":"21" \
-p "21100":"21100" \
-p "21101":"21101" \
-p "21102":"21102" \
-v /home/ftp:/home/vsftpd \
-e FTP_USER=ftpuser \
-e FTP_PASS=123456 \
-e PASV_ADDRESS=127.0.0.1 \
-e PASV_MIN_PORT=21100 \
-e PASV_MAX_PORT=21102 \
--name vsftpd \
--restart=always \
fauria/vsftpd
```







``` txt
参数说明：
/home/ftp:/home/vsftpd：映射 docker 容器 ftp 文件根目录（冒号前面是宿主机的目录）
-p：映射 docker 端口（冒号前面是宿主机的端口）
-e FTP_USER=ftpuser -e FTP_PASS=123456：设置默认的用户名密码
PASV_ADDRESS：宿主机 ip，当需要使用被动模式时必须设置。
PASV_MIN_PORT~ PASV_MAX_PORT：给客服端提供下载服务随机端口号范围，默认 21100-21110，与前面的 docker 端口映射设置成一样。

以命令的方式创建用户与密码，会在容器中的/home/vsftpd 自动创建用户文件夹ftpuser和virtual_users.txt（存放用户名与密码）
```



创建新用户

进入容器，新建用户名和密码

```
docker exec  -it 容器id /bin/bash
```

手动创建用户名文件夹，如test

```
mkdir /home/vsftpd/test
```

写入账号与密码：test 654321

```
vi /etc/vsftpd/virtual_users.txt
```

内容如下：

``` txt
ftpuser
123456
test
654321
```

保存退出后执行如下命令，把登录的验证信息写入数据库。

```
/usr/bin/db_load -T -t hash -f /etc/vsftpd/virtual_users.txt /etc/vsftpd/virtual_users.db
```

最后退出容器，并重启容器可以使用新用户连接 FTP 服务了。

```
 docker restart 容器id
```





## Docker Install PgSQL

https://github.com/docker-library/docs/tree/master/postgres



``` bash
docker run -d \
	--name postgresql11 \
	--restart always \
	-p "5432":"5432" \
	-e POSTGRES_USER=postgres \
	-e POSTGRES_PASSWORD=postgres \
	-e PGDATA=/var/lib/postgresql/data/pgdata \
	-v /docker/postgresql/data:/var/lib/postgresql/data \
	postgres:11

```

## Docker Install Redis



``` bash
docker pull redis:6.2

# 下载对应版本配置文件
https://redis.io/docs/management/config/

# 修改配置
bind * -::*
protected-mode no
appendonly yes

# 示例文件目录结构
docker
-- redis
   -- data
   -- redis.conf


# 命令
docker run -d \
    --name redis6.2 \
    --restart always \
    -p "6379":"6379" \
    -v /docker/redis/redis.conf:/etc/redis/redis.conf \
    -v /docker/redis/data:/data \
    redis:6.2 \
    redis-server /etc/redis/redis.conf


# 命令解释（参考）
docker run \
-p 6379:6379 \  docker与宿主机的端口映射
--name redis \  redis容器的名字
-v /docker/redis/redis.conf:/etc/redis/redis.conf \  挂载redis.conf文件
-v /docker/redis/data:/data \  挂在redis的持久化数据
--restart=always \  设置redis容器随docker启动而自启动
-d \后台运行并返回容器id
redis:7.0.4 \
redis-server /etc/redis/redis.conf \  指定redis在docker中的配置文件路径，后台启动redis

```







