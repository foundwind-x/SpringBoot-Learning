package com.fz.boot.sharding;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

/**
 * 注意：排除DruidDataSourceAutoConfigure.class，因为DruidDataSourceAutoConfigure默认用了SpringBoot顶级的配置spring.datasource.url
 */
@MapperScan("com.fz.boot.sharding.**.mapper*")
@SpringBootApplication(exclude = {JtaAutoConfiguration.class,DruidDataSourceAutoConfigure.class})
public class ShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }

}
