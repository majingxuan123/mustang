package com.springcloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 */
@SpringBootApplication(scanBasePackages="com.springcloudstudy")
/** 增加eureka客户端 用作ribbon负载均衡 */
@EnableEurekaClient
/**   开启feign客户端   basepackage必须包含 service接口  */
@EnableFeignClients(basePackages = {"com.springcloudstudy"})
public class MustangConsumerDeptFeign8102Application {

	public static void main(String[] args) {
		SpringApplication.run(MustangConsumerDeptFeign8102Application.class, args);
	}

}
