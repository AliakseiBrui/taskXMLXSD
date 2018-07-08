package com.epam.task4.dao;

import com.epam.task4.entity.User;

public interface UserTableDao {

    User findUserByLogin(String login) throws DaoException;
}
