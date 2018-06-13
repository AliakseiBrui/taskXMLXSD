package com.epam.task4.dao;

import com.epam.task4.entity.Entity;

public interface UserTableDAO<K,T extends Entity> {

    T findUserByLogin(String login) throws DAOException;
}
