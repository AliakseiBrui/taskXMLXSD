package com.epam.task4.servlet;

import com.epam.task4.pool.ConnectionPool;
import java.sql.Driver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionPool.INSTANCE.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.INSTANCE.closeAll();
        DriverManager.drivers().forEach((this::deregisterDriver));
    }

    private void deregisterDriver(Driver driver){

        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
