package com.test.task4.dao;

import com.epam.task4.dao.DAOException;
import com.epam.task4.dao.UserDAO;
import com.epam.task4.factory.UserFactory;
import com.epam.task4.pool.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserDAOTest {
    private UserDAO userDAO = new UserDAO();
    @BeforeClass
    public void setUp(){
        ConnectionPool.INSTANCE.init();
    }
    @AfterClass
    public void afterTest(){
        ConnectionPool.INSTANCE.closeAll();
    }
    @Test
    public void testFindUserById1() throws DAOException {
        Assert.assertNotNull(userDAO.findEntityById(2));
    }

    @Test
    public void testFindUserById2() throws DAOException{
        Assert.assertNull(userDAO.findEntityById(1));
    }

    @Test
    public void testFindUserByLogin1() throws DAOException {
        Assert.assertNotNull(userDAO.findUserByLogin("alex"));
    }

    @Test
    public void testFindUserByLogin2() throws DAOException{
        Assert.assertNull(userDAO.findUserByLogin("qwegsdpg[kosdf"));
    }

    @Test(expectedExceptions = DAOException.class)
    public void testCreate1() throws DAOException{
        userDAO.create(UserFactory.createUser("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","1234"));
    }

    @Test
    public void testDelete1() throws DAOException{
        Assert.assertFalse(userDAO.delete(1));
    }
}
