package com.rabbonrule;

import com.dynamic.cloud.ExcludeFromComponentScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义ribbon的负载均衡规则类
 *
 */
@Configuration
@ExcludeFromComponentScan
public class MyCustomRule {


    @Bean
    public IRule MyCustomRule(){


        return new RandomRule();
//        return new WeightedResponseTimeRule();

        //自定义的负载均衡规则
//        return new CustomRuleImple();
    }

}
