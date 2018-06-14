package com.epam.task4.commandhandler;

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

        if(register(registerLogin,registerPassword)){
            //To authorization page + successful registration
            request.setAttribute("devicePageMessage","Registration succeeded");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else{
            //To registration page + error
            request.setAttribute("errorMessage","Something was wrong");
            request.getRequestDispatcher("jsp/register.jsp").forward(request,response);
        }
    }

    private boolean register(String login, String password){
        UserDAO userDAO = new UserDAO();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        try {
            User user = UserFactory.createUser(login,passwordEncoder
                    .encryptPassword(password));
            return userDAO.create(user);

        }catch (DAOException e){
            throw new RuntimeException(e);
        }
    }
}
