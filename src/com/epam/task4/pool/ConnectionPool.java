package com.epam.task4.pool;

import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private static final int DEFAULT_POOL_SIZE = 10;
    private static final String DB_URL = "jdbc:mysql://localhost/xml";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "27031998";
    private LinkedBlockingQueue<SafeConnection> connectionQueue = new LinkedBlockingQueue<>();
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    public SafeConnection takeConnection(){

        try{
            return connectionQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public void returnConnection(SafeConnection connection){

        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void closeAll(){

        connectionQueue.forEach(SafeConnection::closeConnection);
        connectionQueue.clear();
    }

    public void init(){
        LOGGER.debug("Initializing connection pool.");

        try {
            DriverManager.registerDriver(new Driver());
            Properties dbProperties = new Properties();
            dbProperties.put("user", DEFAULT_USER);
            dbProperties.put("password", DEFAULT_PASS);
            dbProperties.put("autoReconnect", "true");
            dbProperties.put("characterEncoding", "UTF-8");
            dbProperties.put("useUnicode", "true");

            for(int i = 0; i < DEFAULT_POOL_SIZE; i++){
                Connection connection = DriverManager.getConnection(DB_URL,dbProperties);
                SafeConnection safeConnection = new SafeConnection(connection);
                connectionQueue.put(safeConnection);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while creating connections for connection pool.",e);
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while creating connections for connection pool.",e);
            Thread.currentThread().interrupt();
        }
    }
}
