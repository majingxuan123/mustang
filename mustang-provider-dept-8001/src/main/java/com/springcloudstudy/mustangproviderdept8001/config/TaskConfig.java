package com.springcloudstudy.mustangproviderdept8001.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * <ul>
 * <li>文件名称: TaskConfig</li>
 * <li>文件描述:</li>
 * <li>版权所有: 版权所有(C) 2017</li>
 * <li>公 司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要:</li>
 * <li>其他说明:</li>
 * <li>创建日期:2019/4/10</li>
 * </ul>
 * <ul>
 * <li>修改记录:</li>
 * <li>版 本 号:</li>
 * <li>修改日期:</li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author majx
 * @version 1.0.0
 */

@Configuration
@ComponentScan("com.springcloudstudy.mustangproviderdept8003.config")
@EnableAsync
@Data
@ConfigurationProperties(prefix = "thread.taskconfig")
public class TaskConfig implements AsyncConfigurer {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 最大连接数
     */
    private int corePoolSize;
    /**
     * 并发数
     */
    private int maxPoolSize;
    /**
     * 创建连接最长时间
     */
    private int queueCapacity;
    /**
     * 取到连接最长时间
     */
    private int keepAliveSeconds;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置线程数
        taskExecutor.setCorePoolSize(getCorePoolSize());
        //线程池最大值大小
        taskExecutor.setMaxPoolSize(getMaxPoolSize());
        //设置阻塞队列
        taskExecutor.setQueueCapacity(getQueueCapacity());
        //允许的空闲时间
        taskExecutor.setKeepAliveSeconds(getKeepAliveSeconds());

        //阻塞队列处理方式
        taskExecutor.setRejectedExecutionHandler((r, executor) -> {
            if (!executor.isShutdown()) {
                try {
//                    log.info("开始获取线程...");
                    executor.getQueue().put(r);
//                    log.info("结束获取线程...");
                } catch (InterruptedException e) {
                    log.error(e.toString(), e);
                    Thread.currentThread().interrupt();
                }
            }
        });
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

}
