package com.springcloudstudy.mustangconsumerdept8101.config;

import com.netflix.loadbalancer.*;
import com.springcloudstudy.mustangbase.utils.ftputils.FtpUtil;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <ul>
 * <li>文件名称：RestTemplateConfig</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2019</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2020/1/18 21:53</li>
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
public class RestTemplateConfig {

    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    /**
     * 配置客户端ribbon负载均衡 默认是轮询一个个循环
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        // 添加内容转换器,使用默认的内容转换器
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        // 设置编码格式为UTF-8
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (item.getClass() == StringHttpMessageConverter.class) {
                converterTarget = item;
                break;
            }
        }
        if (converterTarget != null) {
            converterList.remove(converterTarget);
        }
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(1, converter);
        logger.info("-----restTemplate-----初始化完成");
        return restTemplate;
    }

    /**
     * 重写ribbon的负载均衡规则
     *
     * 使用默认的ribbon算法
     *
     * @return
     */
//    @Bean
    public IRule systemNormalRule() {
        //随机
        return new RandomRule();
        /**
         *  过滤掉由于多次访问故障而处于断路器跳闸状态的路由
         *  并发连接数超过阀值的服务  然后对剩余的服务端按照轮询
         */
//        return new AvailabilityFilteringRule();

        /**
         *  根据各个服务的响应时间来计算权重 速度快的分的多
         *  一开始没有数据用来计算的时候 先按照默认的轮询
         */
//        return new WeightedResponseTimeRule();
        /**
         *  先按照默认的轮询 如果失败了几次之后 就不再访问到这里了
         *
         */
//        return new RetryRule();
        /**
         * 过滤掉因多次访问故障处于断路器跳闸状态的服务
         * 然后在剩余之中选择并发量较少的
         */
//        return new RetryRule();
        /**
         * 默认规则  复合判断server所在服务器性能
         * 和server的可用性选择服务器
         */
//        return new ZoneAvoidanceRule();

    }





    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    @Bean
    public HttpClient httpClient() {
        // 长连接保持30秒
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        //设置整个连接池最大连接数 根据自己的场景决定
        connectionManager.setMaxTotal(500);
        //同路由的并发数,路由是对maxTotal的细分
        connectionManager.setDefaultMaxPerRoute(500);
        //requestConfig
        RequestConfig requestConfig = RequestConfig.custom()
                //服务器返回数据(response)的时间，超过该时间抛出read timeout
                .setSocketTimeout(10000)
                //连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
                .setConnectTimeout(5000)
                //从连接池中获取连接的超时时间，超过该时间未拿到可用连接，会抛出org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
                .setConnectionRequestTimeout(500)
                .build();
        //headers
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        headers.add(new BasicHeader("Content-type", "application/json;charset=UTF-8"));

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .setDefaultHeaders(headers)
                // 保持长连接配置，需要在头添加Keep-Alive
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                //重试次数，默认是3次，没有开启
                .setRetryHandler(new DefaultHttpRequestRetryHandler(2, true))
                .build();
    }

}
