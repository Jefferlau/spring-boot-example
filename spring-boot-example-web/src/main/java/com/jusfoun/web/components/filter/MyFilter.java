package com.jusfoun.web.components.filter;

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
 * 当 Filter 无序时使用 ServletComponentScan 扫描即可。
 * 当 Filter 需要排序时，见 WebConfig 中的 FilterRegistrationBean 配置，
 * 此时 name 和 urlPatterns 都在 FilterRegistrationBean 里配置，order 越小越靠前。
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-05
 */

@WebFilter(filterName = "myFilter", urlPatterns = {"/*"})
public class MyFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("--- filter init ---");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("--- do filter ---");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        if (logger.isInfoEnabled()) {
            logger.info("--- filter destroy ---");
        }
    }
}
