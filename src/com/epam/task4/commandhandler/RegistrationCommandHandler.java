package com.epam.task4.commandhandler;

import com.epam.task4.constant.JSP;
import com.epam.task4.dao.DAOException;
import com.epam.task4.dao.UserDAO;
import com.epam.task4.encoder.PasswordEncoder;
import com.epam.task4.entity.User;
import com.epam.task4.factory.UserFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommandHandler implements CommandHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        String registerLogin = request.getParameter("login");
        String registerPassword = request.getParameter("password");
        StringBuilder errorMessage = new StringBuilder();

        if(register(registerLogin,registerPassword,errorMessage)){

            request.setAttribute("message","Registration succeeded");
            request.getRequestDispatcher(JSP.AUTHORIZATION_PAGE).forward(request,response);
        }else{

            request.setAttribute("errorMessage",errorMessage);
            request.getRequestDispatcher(JSP.REGISTRATION_PAGE).forward(request,response);
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
            errorMessage.append(e.getMessage());
        }
        return false;
    }
}
