package com.epam.task4.service;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.PagePath;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.dao.DAOException;
import com.epam.task4.dao.UserDAO;
import com.epam.task4.encoder.PasswordEncoder;
import com.epam.task4.entity.Router;
import com.epam.task4.entity.User;
import com.epam.task4.factory.RouterFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public class AuthorizationService implements CommandService {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationService.class);
    @Override
    public void process(Map<String, String> parameterMap, Map<String, Object> attributeMap)
            throws ServletException, IOException {

        String authorizationLogin = parameterMap.get(ParameterConstant.LOGIN_PARAMETER);
        String authorizationPassword = parameterMap.get(ParameterConstant.PASSWORD_PARAMETER);
        StringBuilder errorMessage = new StringBuilder();

        if(authorize(authorizationLogin,authorizationPassword,errorMessage)){
            attributeMap.put(AttributeConstant.LOGGED_IN_ATTRIBUTE,true);
            attributeMap.put(AttributeConstant.ROUTER_ATTRIBUTE, RouterFactory
                    .createAnswer(Router.RouteType.REDIRECT,PagePath.MAIN_PAGE));
        }else{
            attributeMap.put(AttributeConstant.LOGGED_IN_ATTRIBUTE,false);
            attributeMap.put(AttributeConstant.ERROR_MESSAGE_ATTRIBUTE,errorMessage);
            attributeMap.put(AttributeConstant.ROUTER_ATTRIBUTE, RouterFactory
                    .createAnswer(Router.RouteType.FORWARD,PagePath.AUTHORIZATION_PAGE));
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
            LOGGER.error("Exception during user authorization.",e);
            errorMessage.append("Error code: ").append(e.getErrorCode()).append(". ").append(e.getMessage());
        }
        return false;
    }
}
