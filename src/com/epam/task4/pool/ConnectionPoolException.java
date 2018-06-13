package com.epam.task4.pool;

public class ConnectionPoolException extends RuntimeException {

    public ConnectionPoolException(String message, Throwable throwable){
        super(message,throwable);
    }

    ConnectionPoolException(Throwable throwable){
        super(throwable);
    }
}
