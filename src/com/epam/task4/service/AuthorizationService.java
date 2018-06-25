package com.epam.task4.service;

import com.epam.task4.constant.PagePath;
import com.epam.task4.dao.DAOException;
import com.epam.task4.dao.UserDAO;
import com.epam.task4.encoder.PasswordEncoder;
import com.epam.task4.entity.AnswerType;
import com.epam.task4.entity.User;
import com.epam.task4.factory.AnswerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

public class AuthorizationService implements CommandService {
    @Override
    public void process(HashMap<String, String> parameterMap, HashMap<String, Object> attributeMap)
            throws ServletException, IOException {

        String authorizationLogin = parameterMap.get("login");
        String authorizationPassword = parameterMap.get("password");
        StringBuilder errorMessage = new StringBuilder();

        if(authorize(authorizationLogin,authorizationPassword,errorMessage)){
            attributeMap.put("logged in",true);
            attributeMap.put(ANSWER_ATTRIBUTE, AnswerFactory
                    .createAnswer(AnswerType.REDIRECT,PagePath.MAIN_PAGE));
        }else{
            attributeMap.put("logged in",false);
            attributeMap.put("errorMessage",errorMessage);
            attributeMap.put(ANSWER_ATTRIBUTE, AnswerFactory
                    .createAnswer(AnswerType.FORWARD,PagePath.AUTHORIZATION_PAGE));
        }
    }

    private boolean authorize(String login, String password, StringBuilder errorMessage){
        UserDAO userDAO = new UserDAO();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        try {
            User user = userDAO.findUserByLogin(login);

            if(user != null && passwordEncoder.encryptPassword(password).equals(user.getPass())){

                return true;
            }
            errorMessage.append("Wrong login or password");
        } catch (DAOException e) {
            errorMessage.append("Error code: ").append(e.getErrorCode()).append(". ").append(e.getMessage());
        }
        return false;
    }
}
