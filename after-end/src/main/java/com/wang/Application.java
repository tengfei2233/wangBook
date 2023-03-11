package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-10-20:14
 * @description
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("~~~~~~~~~~~~~~系统启动成功~~~~~~~~~~~~~~");
    }

}
