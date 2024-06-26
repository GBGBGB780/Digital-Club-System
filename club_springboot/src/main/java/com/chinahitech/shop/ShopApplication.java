package com.chinahitech.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.chinahitech")
@MapperScan("com.chinahitech.shop.mapper")
public class ShopApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ShopApplication.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ShopApplication.class);
//        SpringApplication.run(ShopApplication.class, args);
        Environment env = app.run(args).getEnvironment();
        LOG.info("原神 启动！");
//        LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
//        LOG.info("测试地址: \thttp://127.0.0.1:{}/student/login", env.getProperty("server.port"));
    }

}
