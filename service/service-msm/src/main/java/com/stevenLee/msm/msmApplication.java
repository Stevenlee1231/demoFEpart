package com.stevenLee.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.annotations.Cacheable;

@ComponentScan("com.stevenLee")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class msmApplication {
    public static void main(String[] args) {
        SpringApplication.run(msmApplication.class, args);
    }
}
