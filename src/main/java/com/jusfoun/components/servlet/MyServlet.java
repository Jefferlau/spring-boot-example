package com.jusfoun.components.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 刘体阳 jefferlzu@gmail.com
 *         Created on 2016-12-05
 */
@WebServlet(urlPatterns = {"/myServlet/*"}, description = "Servlet 说明")
public class MyServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(MyServlet.class);

    private static final long serialVersionUID = 4455523440715434980L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (logger.isInfoEnabled()) {
            logger.info("--- doGet ---");
        }
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (logger.isInfoEnabled()) {
            logger.info("--- doPost ---");
        }
        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        if (logger.isInfoEnabled()) {
            logger.info("--- destroy ---");
        }
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("--- init config ---");
        }
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        if (logger.isInfoEnabled()) {
            logger.info("--- init ---");
        }
        super.init();
    }
}
