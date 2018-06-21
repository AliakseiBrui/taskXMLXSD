package com.epam.task4.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private SQLDriverManager sqlDriverManager = new SQLDriverManager();

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

    void closeAll(){

        connectionQueue.forEach(SafeConnection::closeConnection);
        connectionQueue.clear();
        sqlDriverManager.deregisterDrivers();
    }

    void init(){
        LOGGER.debug("Initializing connection pool.");
        sqlDriverManager.registerDriver();
        Properties dbProperties = new Properties();
        dbProperties.put("user", DEFAULT_USER);
        dbProperties.put("password", DEFAULT_PASS);
        dbProperties.put("autoReconnect", "true");
        dbProperties.put("characterEncoding", "UTF-8");
        dbProperties.put("useUnicode", "true");

        try {

            for(int i = 0; i < DEFAULT_POOL_SIZE; i++){
                connectionQueue.put(createConnection(dbProperties));
            }
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted while creating connections for connection pool.",e);
            Thread.currentThread().interrupt();
        }
    }

    private SafeConnection createConnection(Properties dbProperties){

        try {
            return new SafeConnection(DriverManager.getConnection(DB_URL,dbProperties));
        } catch (SQLException e) {
            LOGGER.error("Exception while creating connection.",e);
            throw new RuntimeException(e);
        }
    }
}
