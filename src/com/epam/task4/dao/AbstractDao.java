package com.epam.task4.dao;

import com.epam.task4.entity.Entity;

import java.util.List;

public abstract class AbstractDao<K,T extends Entity> {
    public abstract List<T> findAll() throws DaoException;
    public abstract T findEntityById(K id) throws DaoException;
    public abstract boolean delete(K id) throws DaoException;
    public abstract boolean delete(T entity) throws DaoException;
    public abstract boolean create(T entity) throws DaoException;
    public abstract boolean update(T entity) throws DaoException;
}
