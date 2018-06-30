package com.epam.task4.pool;

import com.epam.task4.config.DataBaseConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private static final int DEFAULT_POOL_SIZE = 10;
    private LinkedBlockingQueue<SafeConnection> connectionQueue = new LinkedBlockingQueue<>();
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private SQLDriverManager sqlDriverManager = new SQLDriverManager();
    private boolean canInitialize = true;

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

    public void init(){

        if(canInitialize) {
            LOGGER.debug("Initializing connection pool.");
            sqlDriverManager.registerDriver();

            try {

                for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                    connectionQueue.put(createConnection(DataBaseConfigurator.INSTANCE.getDbProperties()));
                }
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted while creating connections for connection pool.", e);
                Thread.currentThread().interrupt();
            }
            canInitialize = false;
        }
    }

    public void closeAll(){

       for(int i=0; i<DEFAULT_POOL_SIZE; i++){

            try {
                connectionQueue.take().closeConnection();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        sqlDriverManager.deregisterDrivers();
    }



    private SafeConnection createConnection(Properties dbProperties){

        try {
            return new SafeConnection(DriverManager.getConnection((String) dbProperties.get("url"),dbProperties));
        } catch (SQLException e) {
            LOGGER.fatal("Exception while creating connection.",e);
            throw new RuntimeException(e);
        }
    }
}
