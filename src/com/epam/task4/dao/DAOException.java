package com.epam.task4.dao;

import java.sql.SQLException;

public class DAOException extends SQLException {

    DAOException(String message, Throwable cause){
        super(message,cause);
    }

    DAOException(Throwable cause){
        super(cause);
    }
}
