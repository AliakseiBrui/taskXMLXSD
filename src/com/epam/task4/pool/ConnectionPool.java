package com.epam.task4.pool;

import com.mysql.jdbc.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public enum ConnectionPool {
    INSTANCE;

    private static final int DEFAULT_MAX_SIZE = 10;
    private static final String DB_URL = "jdbc:mysql://localhost/xml";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "27031998";
    private LinkedBlockingQueue<SafeConnection> connectionQueue = new LinkedBlockingQueue<>();
    private final ReentrantLock locker = new ReentrantLock();
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    ConnectionPool(){
    }

    public SafeConnection takeConnection(){
        locker.lock();

        try{
            return connectionQueue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
        return null;
    }

    public void returnConnection(SafeConnection connection){
        locker.lock();

        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }

    public void closeAll(){

        connectionQueue.forEach(connection -> {

            if (connection != null) {

                try {
                    connection.closeConnection();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        connectionQueue.clear();
    }

    public void init(){
        LOGGER.debug("Initiating connection pool.");

        try {
            DriverManager.registerDriver(new Driver());
            Properties dbProperties = new Properties();
            dbProperties.put("user", DEFAULT_USER);
            dbProperties.put("password", DEFAULT_PASS);
            dbProperties.put("autoReconnect", "true");
            dbProperties.put("characterEncoding", "UTF-8");
            dbProperties.put("useUnicode", "true");

            for(int i=0;i < DEFAULT_MAX_SIZE; i++){
                Connection connection = DriverManager.getConnection(DB_URL,dbProperties);
                SafeConnection safeConnection = new SafeConnection(connection);
                connectionQueue.put(safeConnection);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while creating connections for connection pool.");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }
}
