package com.epam.task4.dao;

import com.epam.task4.entity.User;

public interface UserTableDAO {

    User findUserByLogin(String login) throws DAOException;
}
