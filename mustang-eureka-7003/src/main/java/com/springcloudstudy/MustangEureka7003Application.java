package com.springcloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MustangEureka7003Application {

    public static void main(String[] args) {
        SpringApplication.run(MustangEureka7003Application.class, args);
    }

}
