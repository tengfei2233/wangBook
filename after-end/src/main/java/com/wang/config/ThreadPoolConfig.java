package com.wang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author feige
 * @version v1.0
 * @date 2023-03-31-22:02
 * @description 线程池配置
 */

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    // 核心线程数
    private static int corePoolSize = 5;
    // 阻塞队列大小
    private static int queueSize = 50;
    // 最大线程数
    private static int maxPoolSize = 20;
    // 最大线程空闲存活时间/s
    private static int aliveTime=10;

    @Bean
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(aliveTime);
        executor.setThreadNamePrefix("thread-pool-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
