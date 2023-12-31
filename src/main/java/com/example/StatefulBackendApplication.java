package com.example;

import com.example.config.Knife4jConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Knife4jConfig.class)
//@MapperScan("com.example.mapper")
//若 MyBatisPlusConfig 中已经配置了 @MapperScan，则此处可省略，否则会出现 Bean name 重复定义的问题
public class StatefulBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatefulBackendApplication.class, args);
    }

}
