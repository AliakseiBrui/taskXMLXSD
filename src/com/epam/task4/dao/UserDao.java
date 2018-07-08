package com.epam.task4.dao;

import com.epam.task4.constant.UserColumn;
import com.epam.task4.entity.User;
import com.epam.task4.factory.UserFactory;
import com.epam.task4.pool.ConnectionPool;
import com.epam.task4.pool.SafeConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDao extends AbstractDao<Integer,User> implements UserTableDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
    private static final String SELECT_USER_BY_ID = "SELECT xml.users.id,xml.users.login,xml.users.password FROM xml.users WHERE xml.users.id = ?";
    private static final String SELECT_ALL_FROM_USER = "SELECT xml.users.id,xml.users.login,xml.users.password FROM xml.users";
    private static final String DELETE_USER_BY_ID = "DELETE FROM xml.users WHERE xml.users.id = ?";
    private static final String INSERT_USER = "INSERT INTO xml.users(xml.users.login, xml.users.password) VALUES (?,?)";
    private static final String UPDATE_USER = "UPDATE xml.users SET xml.users.login = ?, xml.users.password = ? WHERE xml.users.id = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT xml.users.id,xml.users.login,xml.users.password FROM xml.users WHERE xml.users.login = ?";

    @Override
    public List<User> findAll() throws DaoException {
        LOGGER.debug("Selecting all users.");
        List<User> userList = new ArrayList<>();


        try(SafeConnection connection = ConnectionPool.INSTANCE.takeConnection();
            Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USER)){

            if(resultSet!=null){

                while (resultSet.next()){
                    userList.add(UserFactory
                            .createUser(resultSet.getInt(UserColumn.ID),resultSet.getString(UserColumn.LOGIN),resultSet.getString(UserColumn.PASSWORD)));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while selecting all users.",e);
        }
        return userList;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        LOGGER.debug("Selecting user by id. Id: "+id);
        ResultSet resultSet = null;

        try (SafeConnection connection = ConnectionPool.INSTANCE.takeConnection();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_USER_BY_ID)){

            if (preparedStatement != null) {
                preparedStatement.setInt(1,id);
                resultSet = preparedStatement.executeQuery();
            }

            if(resultSet!=null && resultSet.next()){
                return UserFactory.createUser(resultSet.getInt(UserColumn.ID),resultSet.getString(UserColumn.LOGIN),resultSet.getString(UserColumn.PASSWORD));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while selecting user by id",e);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        LOGGER.debug("Deleting user by id. Id: "+id);

        try(SafeConnection connection = ConnectionPool.INSTANCE.takeConnection();
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(DELETE_USER_BY_ID);){

            if (preparedStatement != null) {
                preparedStatement.setInt(1,id);

                return preparedStatement.executeUpdate()>0;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while deleting user.",e);
        }
        return false;
    }

    @Override
    public boolean delete(User entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(User entity) throws DaoException {
        LOGGER.debug("Creating new user. " + entity);

        try (SafeConnection connection = ConnectionPool.INSTANCE.takeConnection();
             PreparedStatement preparedStatement=Objects.requireNonNull(connection).prepareStatement(INSERT_USER);){

            if(preparedStatement!=null){
                preparedStatement.setString(1,entity.getLogin());
                preparedStatement.setString(2,entity.getPass());
                return preparedStatement.executeUpdate()>0;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while inserting user.",e);
        }
        return false;
    }

    @Override
    public boolean update(User entity) throws DaoException {
        LOGGER.debug("Updating user." + entity);

        try(SafeConnection connection = ConnectionPool.INSTANCE.takeConnection();
            PreparedStatement preparedStatement=Objects.requireNonNull(connection).prepareStatement(UPDATE_USER);){

            if(preparedStatement != null){
                preparedStatement.setInt(3,entity.getId());
                preparedStatement.setString(1,entity.getLogin());
                preparedStatement.setString(2,entity.getPass());

                return preparedStatement.executeUpdate()>0;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while updating user",e);
        }
        return false;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        LOGGER.debug("Selecting user by login. Login: "+login);
        ResultSet resultSet = null;

        try(SafeConnection connection = ConnectionPool.INSTANCE.takeConnection();
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_USER_BY_LOGIN);){

            if(preparedStatement!=null){
                preparedStatement.setString(1,login);
                resultSet = preparedStatement.executeQuery();
            }

            if(resultSet!=null && resultSet.next()){
                return UserFactory.createUser(resultSet.getInt(UserColumn.ID),resultSet.getString(UserColumn.LOGIN),resultSet.getString(UserColumn.PASSWORD));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception while selecting user by login",e);
        }
        return null;
    }
}
