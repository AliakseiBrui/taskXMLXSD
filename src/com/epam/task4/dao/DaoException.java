package com.epam.task4.dao;

import java.sql.SQLException;

public class DaoException extends SQLException {

    DaoException(String message, Throwable cause){
        super(message,cause);
    }

    DaoException(Throwable cause){
        super(cause);
    }
}
