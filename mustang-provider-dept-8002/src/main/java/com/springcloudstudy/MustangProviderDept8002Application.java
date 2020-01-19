package com.springcloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages="com.springcloudstudy.**")
/**
 * 本服务启动后会自动注册金eureka
 */
@EnableEurekaClient
/**
 * 开启服务发现
 */
@EnableDiscoveryClient
public class MustangProviderDept8002Application {

    public static void main(String[] args) {
        SpringApplication.run(MustangProviderDept8002Application.class, args);
    }

}
