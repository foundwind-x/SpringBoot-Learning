### 本项目主要用于测试springboot与分库分表中间件sharding-jdbc的整合,orm框架使用mybatisplus


#### 功能说明
基于springboot+mybatisplus的分布式敏捷开发项目，包含以下功能：
- 数据使用mysql
- orm框架使用mybatisplus
- 基于sharding-jdbc-spring-boot-starter的分库分表
- 注：数据库分库分表使用依赖为
````
<dependency>
    <groupId>org.apache.shardingsphere</groupId>
    <artifactId>shardingsphere-jdbc-spring-boot-starter</artifactId>
    <version>${shardingsphere.version}</version>
</dependency>
````

#### 参考
- 官网：https://shardingsphere.apache.org/document/current/cn/overview/
- 例子：
  - https://gitee.com/Sharding-Sphere/sharding-sphere/blob/master/examples/shardingsphere-jdbc-example/sharding-example/sharding-spring-boot-mybatis-example
  - https://gitee.com/luci-fast/mybatis-plus-sharding