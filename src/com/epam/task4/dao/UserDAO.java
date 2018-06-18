package com.epam.task4.dao;

import com.epam.task4.entity.User;
import com.epam.task4.factory.UserFactory;
import com.epam.task4.pool.ConnectionPool;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO extends AbstractDAO<Integer,User> implements UserTableDAO<Integer, User> {
    private static final String ID_COLUMN = "id";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASS_COLUMN = "password";
    private static final String SELECT_USER_BY_ID = "SELECT xml.users.id,xml.users.login,xml.users.password FROM xml.users WHERE xml.users.id = ?";
    private static final String SELECT_ALL_FROM_USER = "SELECT xml.users.id,xml.users.login,xml.users.password FROM xml.users";
    private static final String DELETE_USER_BY_ID = "DELETE FROM xml.users WHERE xml.users.id = ?";
    private static final String INSERT_USER = "INSERT INTO xml.users(xml.users.login, xml.users.password) VALUES (?,?)";
    private static final String UPDATE_USER = "UPDATE xml.users SET xml.users.login = ?, xml.users.password = ? WHERE xml.users.id = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT xml.users.id,xml.users.login,xml.users.password FROM xml.users WHERE xml.users.login = ?";

    @Override
    public List<User> findAll() throws DAOException {
        List<User> userList = new ArrayList<>();
        Connection connection = ConnectionPool.INSTANCE.takeConnection();

        try(Statement statement = Objects.requireNonNull(connection).createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_USER)){

            if(resultSet!=null){

                while (resultSet.next()){
                    userList.add(UserFactory
                            .createUser(resultSet.getInt(ID_COLUMN),resultSet.getString(LOGIN_COLUMN),resultSet.getString(PASS_COLUMN)));
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
        return userList;
    }

    @Override
    public User findEntityById(Integer id) throws DAOException{
        Connection connection = ConnectionPool.INSTANCE.takeConnection();
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_USER_BY_ID)){

            if (preparedStatement != null) {
                preparedStatement.setInt(1,id);
                resultSet = preparedStatement.executeQuery();
            }

            if(resultSet!=null && resultSet.next()){
                return UserFactory.createUser(resultSet.getInt(ID_COLUMN),resultSet.getString(LOGIN_COLUMN),resultSet.getString(PASS_COLUMN));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet);
            returnConnection(connection);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DAOException{
        Connection connection = ConnectionPool.INSTANCE.takeConnection();

        try(PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(DELETE_USER_BY_ID);){

            if (preparedStatement != null) {
                preparedStatement.setInt(1,id);
                return preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }
        return false;
    }

    @Override
    public boolean delete(User entity) throws DAOException{
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(User entity) throws DAOException{
        Connection connection = ConnectionPool.INSTANCE.takeConnection();

        try (PreparedStatement preparedStatement=Objects.requireNonNull(connection).prepareStatement(INSERT_USER);){

            if(preparedStatement!=null){
                preparedStatement.setString(1,entity.getLogin());
                preparedStatement.setString(2,entity.getPass());
                return preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally {
            returnConnection(connection);
        }
        return false;
    }

    @Override
    public boolean update(User entity) throws DAOException{
        Connection connection = ConnectionPool.INSTANCE.takeConnection();

        try(PreparedStatement preparedStatement=Objects.requireNonNull(connection).prepareStatement(UPDATE_USER);){

            if(preparedStatement != null){
                preparedStatement.setInt(3,entity.getId());
                preparedStatement.setString(1,entity.getLogin());
                preparedStatement.setString(2,entity.getPass());
                return preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(connection);
        }

        return false;
    }

    @Override
    public User findUserByLogin(String login) throws DAOException{
        Connection connection = ConnectionPool.INSTANCE.takeConnection();
        ResultSet resultSet = null;

        try(PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_USER_BY_LOGIN);){

            if(preparedStatement!=null){
                preparedStatement.setString(1,login);
                resultSet = preparedStatement.executeQuery();
            }

            if(resultSet!=null && resultSet.next()){
                return UserFactory.createUser(resultSet.getInt(ID_COLUMN),resultSet.getString(LOGIN_COLUMN),resultSet.getString(PASS_COLUMN));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(resultSet);
            returnConnection(connection);
        }
        return null;
    }
}
