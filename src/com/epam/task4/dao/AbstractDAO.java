package com.epam.task4.dao;

import com.epam.task4.entity.Entity;
import com.epam.task4.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO<K,T extends Entity> {
    public abstract List<T> findAll() throws DAOException;
    public abstract T findEntityById(K id) throws DAOException;
    public abstract boolean delete(K id) throws DAOException;
    public abstract boolean delete(T entity) throws DAOException;
    public abstract boolean create(T entity) throws DAOException;
    public abstract boolean update(T entity) throws DAOException;

    void close(Statement statement) {

        if(statement!=null) {

            try {
                statement.close();
            } catch (SQLException e) {
                //TODO:LOG: can not close statement
            }
        }
    }

    void close(ResultSet resultSet) {

        if(resultSet!=null){

            try{
                resultSet.close();
            } catch (SQLException e) {
                //TODO:LOG: can not close result set
            }
        }
    }

    void returnConnection(Connection connection){

        if(connection!=null){
            ConnectionPool.INSTANCE.returnConnection(connection);
        }
    }

    protected String quote(String data){

        return "\'"+data+"\'";
    }


}
