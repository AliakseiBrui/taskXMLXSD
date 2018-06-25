package com.epam.task4.service;

import com.epam.task4.constant.PagePath;
import com.epam.task4.dao.DAOException;
import com.epam.task4.dao.UserDAO;
import com.epam.task4.encoder.PasswordEncoder;
import com.epam.task4.entity.AnswerType;
import com.epam.task4.entity.User;
import com.epam.task4.factory.AnswerFactory;
import com.epam.task4.factory.UserFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

public class RegistrationService implements CommandService {

    @Override
    public void process(HashMap<String, String> parameterMap, HashMap<String, Object> attributeMap)
            throws ServletException, IOException {

        String registerLogin = parameterMap.get("login");
        String registerPassword = parameterMap.get("password");
        StringBuilder errorMessage = new StringBuilder();

        if(register(registerLogin,registerPassword,errorMessage)){

            attributeMap.put("message","Registration succeeded");
            attributeMap.put(ANSWER_ATTRIBUTE,AnswerFactory
                    .createAnswer(AnswerType.FORWARD,PagePath.AUTHORIZATION_PAGE));
        }else{

            attributeMap.put("errorMessage",errorMessage);
            attributeMap.put(ANSWER_ATTRIBUTE,AnswerFactory
                    .createAnswer(AnswerType.FORWARD,PagePath.REGISTRATION_PAGE));
        }
    }

    private boolean register(String login, String password, StringBuilder errorMessage){
        UserDAO userDAO = new UserDAO();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        try {
            User user = UserFactory.createUser(login,passwordEncoder
                    .encryptPassword(password));
            return userDAO.create(user);
        }catch (DAOException e){
            errorMessage.append("Error code: ").append(e.getErrorCode()).append(". ").append(e.getMessage());
        }
        return false;
    }
}
