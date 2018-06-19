package com.epam.task4.dao;

import com.epam.task4.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<K,T extends Entity> {
    public abstract List<T> findAll() throws DAOException;
    public abstract T findEntityById(K id) throws DAOException;
    public abstract boolean delete(K id) throws DAOException;
    public abstract boolean delete(T entity) throws DAOException;
    public abstract boolean create(T entity) throws DAOException;
    public abstract boolean update(T entity) throws DAOException;
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class);


    void close(ResultSet resultSet) throws DAOException {
        LOGGER.debug("Closing result set.");

        if(resultSet!=null){

            try{
                resultSet.close();
            } catch (SQLException e) {
                throw new DAOException("Cannot close resultSet",e);
            }
        }
    }


}
