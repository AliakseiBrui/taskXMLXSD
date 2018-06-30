package com.epam.task4.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

class SQLDriverManager {
    private static final Logger LOGGER = LogManager.getLogger(SQLDriverManager.class);

    SQLDriverManager(){}

    void registerDriver(){
        LOGGER.debug("Registering drivers.");

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.fatal("Exception in driver registration.",e);
            throw new RuntimeException(e);
        }
    }

    void deregisterDrivers(){
        DriverManager.drivers().forEach((this::deregisterDriver));
    }

    private void deregisterDriver(java.sql.Driver driver){
        LOGGER.debug("Deregistering driver.");

        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            LOGGER.fatal("Exception in driver deregistration",e);
            throw new RuntimeException(e);
        }
    }
}
