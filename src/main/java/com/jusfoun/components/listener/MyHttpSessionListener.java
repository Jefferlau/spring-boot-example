package com.jusfoun.components.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-05
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    private static Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        if (logger.isInfoEnabled()) {
            logger.info("--- session create ---");
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        if (logger.isInfoEnabled()) {
            logger.info("--- session destroy ---");
        }
    }
}
