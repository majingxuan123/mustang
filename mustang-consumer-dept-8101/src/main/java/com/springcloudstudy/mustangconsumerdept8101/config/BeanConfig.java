package com.springcloudstudy.mustangconsumerdept8101.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <ul>
 * <li>文件名称：BeanConfig</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/18 20:18</li>
 * </ul>
 *
 * <ul>
 * <li>修改记录：</li>
 * <li>版 本 号：</li>
 * <li>修改日期：</li>
 * <li>修 改 人：</li>
 * <li>修改内容：</li>
 * </ul>
 *
 * @author majx
 * @version 1.0.0
 */
@Configuration
public class BeanConfig {


    /**
     * 提供多种远程访问http服务方法
     * 可以简单便捷的访问restful服务模版
     * spring提供用于rest访问的客户端模版工具集
     *
     *
     * 使用：
     * url：rest请求地址
     * requestMap：请求参数
     * responseBean。class http相应转换被转换成的对象类型
     *
     * @return
     */
//    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }



}
