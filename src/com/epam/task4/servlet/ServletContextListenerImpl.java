package com.epam.task4.servlet;

import com.epam.task4.config.DataBaseConfigurator;
import com.epam.task4.constant.FilePath;
import com.epam.task4.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataBaseConfigurator.INSTANCE.configureDBProperties(servletContextEvent.getServletContext().getRealPath(File.separator + FilePath.CONFIG_DIRECTORY + File.separator + FilePath.DATABASE_CONFIG_FILE));
        ConnectionPool.INSTANCE.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.INSTANCE.closeAll();
    }
}
