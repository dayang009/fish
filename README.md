## 微服务项目

Hello World!!!



## 依赖版本对比

### 后端

- 更新日期：2023-07-05

| 依赖                           | 本项目版本  | 新版                                                         | 说明                                                         |
| ------------------------------ | ----------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| spring-boot                    | v2.7.13     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2.&metadataUrl=https://s01.oss.sonatype.org/content/repositories/releases/org/springframework/boot/spring-boot-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| spring-cloud                   | v2021.0.8   | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2021&metadataUrl=https://s01.oss.sonatype.org/content/repositories/releases/org/springframework/cloud/spring-cloud-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| spring-cloud-alibaba           | v2021.0.5.0 | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2021.0&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/alibaba/cloud/spring-cloud-alibaba-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x，[Spring Cloud Alibaba](https://github.com/alibaba/spring-cloud-alibaba/blob/2022.x/README-zh.md) |
| spring-boot-admin              | v2.7.10     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2.&metadataUrl=https://oss.sonatype.org/content/repositories/releases/de/codecentric/spring-boot-admin-dependencies/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| spring-authorization-server    | v0.4.2      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=0.&metadataUrl=https://s01.oss.sonatype.org/content/repositories/releases/org/springframework/security/spring-security-oauth2-authorization-server/maven-metadata.xml) | 限制 Spring Boot 2.x                                         |
| mybatis                        | v2.3.1      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=2.&metadataUrl=https://oss.sonatype.org/content/repositories/releases/org/mybatis/spring/boot/mybatis-spring-boot-starter/maven-metadata.xml) | 限制 Spring Boot 2.x，指 mybatis-spring-boot-starter         |
| mybatis-plus                   | v3.5.3.1    | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/baomidou/mybatis-plus-boot-starter/maven-metadata.xml) |                                                              |
| pagehelper-spring-boot-starter | v1.4.7      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/github/pagehelper/pagehelper-spring-boot-starter/maven-metadata.xml) | MyBatis 推荐分页插件                                         |
| dynamic-datasource             | v3.6.1      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&versionPrefix=3.&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/baomidou/dynamic-datasource-spring-boot-starter/maven-metadata.xml) | 指 dynamic-datasource-spring-boot-starter                    |
| druid-spring-boot-starter      | v1.2.18     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/alibaba/druid-spring-boot-starter/maven-metadata.xml) | 德鲁伊链接池                                                 |
| knife4j                        | v4.1.0      | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/github/xiaoymin/knife4j-dependencies/maven-metadata.xml) | Knife4j是一个集Swagger2和OpenAPI3为一体的增强解决方案        |
| springdoc                      | 1.7.0       | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/org/springdoc/springdoc-openapi-ui/maven-metadata.xml) | 用于生成 API doc，支持从 javadoc 中获取字段注释              |
| guava                          | v31.1-jre   | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/google/guava/guava/maven-metadata.xml) |                                                              |
| fastjson                       | v2.0.34     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/com/alibaba/fastjson/maven-metadata.xml) | 使用的是不带英文后缀的版本                                   |
| hutool                         | v5.8.20     | ![img](https://img.shields.io/maven-metadata/v?label=&color=blue&metadataUrl=https://oss.sonatype.org/content/repositories/releases/cn/hutool/hutool-all/maven-metadata.xml) | Hutool 是一个小而全的 Java 工具类库                          |



## Note

yml 和 properties 配置文件格式在线转换网址：https://www.toyaml.com

快速搜索依赖网址：https://mvn.coderead.cn/



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







