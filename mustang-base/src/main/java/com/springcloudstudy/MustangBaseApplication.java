package com.springcloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.springcloudstudy.**")
public class MustangBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MustangBaseApplication.class, args);
    }

}
