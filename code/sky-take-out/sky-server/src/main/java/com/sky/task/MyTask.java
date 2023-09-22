package com.sky.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义任务定时类
 */
@Component
public class MyTask {

    private static final Logger log = LoggerFactory.getLogger(MyTask.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask() {
        log.info("定时器任务开始执行{}", new Date());
    }
}
