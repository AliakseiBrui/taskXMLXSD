package com.epam.task4.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public enum ConnectionPool {
    INSTANCE;

    private static final int DEFAULT_MAX_SIZE = 10;
    private static final String DB_URL = "jdbc:mysql://localhost/xml?useSSL=false&useUnicode=true";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "27031998";
    private LinkedBlockingQueue<Connection> connectionQueue = new LinkedBlockingQueue<>();
    private final ReentrantLock locker = new ReentrantLock();

    public void init(){

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            for(int i=0;i < DEFAULT_MAX_SIZE; i++){
                Connection connection = DriverManager.getConnection(DB_URL,DEFAULT_USER,DEFAULT_PASS);
                connectionQueue.put(connection);
            }
        } catch (SQLException e) {

            throw new ConnectionPoolException(e);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    public Connection takeConnection(){
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

    public void returnConnection(Connection connection){
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
                    connection.close();
                } catch (SQLException e) {
                    throw new ConnectionPoolException(e);
                }
            }
        });

        connectionQueue.clear();
    }
}
