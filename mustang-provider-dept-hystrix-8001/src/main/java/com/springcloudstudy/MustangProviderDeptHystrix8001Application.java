package com.springcloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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
/**
 * 开启服务熔断
 */
@EnableCircuitBreaker
public class MustangProviderDeptHystrix8001Application {

    public static void main(String[] args) {
        SpringApplication.run(MustangProviderDeptHystrix8001Application.class, args);
    }

}
