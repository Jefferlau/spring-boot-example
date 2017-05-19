package com.jusfoun.web.components.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-05
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        if (logger.isInfoEnabled()) {
            logger.info("--- listener init ---");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (logger.isInfoEnabled()) {
            logger.info("--- listener destroy ---");
        }
    }
}
