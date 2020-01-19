package com.springcloudstudy;

import com.dynamic.cloud.ExcludeFromComponentScan;
import com.rabbonrule.MyCustomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
/** 增加eureka客户端 用作ribbon负载均衡 */
@EnableEurekaClient
/** 使用自定义的ribbon负载规则 需要这个注解   针对name对应的提供者    配置类是  configuration
 *	配置类不能被spring默认的scanner扫描到（springboot启动包以及子包下）否则就会全局生效
 */
@RibbonClient(name = "MUSTANG-DEPT-PROVIDER" ,configuration = MyCustomRule.class)
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})
public class MustangConsumerDept8101Application {

	public static void main(String[] args) {
		SpringApplication.run(MustangConsumerDept8101Application.class, args);
	}

}
