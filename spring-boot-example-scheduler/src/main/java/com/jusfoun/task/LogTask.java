package com.jusfoun.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-11-21
 */
@Component
public class LogTask {
    private static Logger logger = LoggerFactory.getLogger(LogTask.class);

    /**
     * 缩短定时任务执行间隔，便于查看执行效果。
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void WriteLog() {
        logger.debug("debug log message.");
        logger.info("info log message.");
        logger.warn("warn log message.");
        logger.error("error log message.");
    }
}
