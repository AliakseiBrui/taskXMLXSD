package com.epam.task4.factory;

import com.epam.task4.entity.User;

public class UserFactory {
    private UserFactory(){}

    public static User createUser(Integer id,String login, String password){
        return new User(id,login,password);
    }

    public static User createUser(String login, String password){
        return new User(login,password);
    }
}
