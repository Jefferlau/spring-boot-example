package com.jusfoun.components.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-05
 */

@WebFilter(filterName = "myFilter1", urlPatterns = {"/*"})
public class MyFilter1 implements Filter {
    private static Logger logger = LoggerFactory.getLogger(MyFilter1.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("--- filter1 init ---");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("--- do filter1 ---");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        if (logger.isInfoEnabled()) {
            logger.info("--- filter1 destroy ---");
        }
    }
}
