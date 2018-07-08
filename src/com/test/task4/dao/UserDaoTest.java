package com.test.task4.dao;

import com.epam.task4.dao.DaoException;
import com.epam.task4.dao.UserDao;
import com.epam.task4.factory.UserFactory;
import com.epam.task4.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserDaoTest {
    private UserDao userDAO = new UserDao();
    @BeforeClass
    public void setUp(){
        ConnectionPool.INSTANCE.init();
    }
    @AfterClass
    public void afterTest(){
        ConnectionPool.INSTANCE.closeAll();
    }
    @Test
    public void testFindUserById1() throws DaoException {
        Assert.assertNotNull(userDAO.findEntityById(2));
    }

    @Test
    public void testFindUserById2() throws DaoException {
        Assert.assertNull(userDAO.findEntityById(1));
    }

    @Test
    public void testFindUserByLogin1() throws DaoException {
        Assert.assertNotNull(userDAO.findUserByLogin("alex"));
    }

    @Test
    public void testFindUserByLogin2() throws DaoException {
        Assert.assertNull(userDAO.findUserByLogin("qwegsdpg[kosdf"));
    }

    @Test(expectedExceptions = DaoException.class)
    public void testCreate1() throws DaoException {
        userDAO.create(UserFactory.createUser("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","1234"));
    }

    @Test
    public void testDelete1() throws DaoException {
        Assert.assertFalse(userDAO.delete(1));
    }
}
