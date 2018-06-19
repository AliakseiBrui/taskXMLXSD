package com.epam.task4.commandhandler;

import com.epam.task4.constant.JSP;
import com.epam.task4.dao.DAOException;
import com.epam.task4.dao.UserDAO;
import com.epam.task4.encoder.PasswordEncoder;
import com.epam.task4.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationCommandHandler implements CommandHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        String authorizationLogin = request.getParameter("login");
        String authorizationPassword = request.getParameter("password");
        StringBuilder errorMessage = new StringBuilder();

        if(authorize(authorizationLogin,authorizationPassword,errorMessage)){

            request.getSession().setAttribute("login",authorizationLogin);
            response.sendRedirect(JSP.MAIN_PAGE);
        }else{

            request.setAttribute("errorMessage",errorMessage);
            request.getRequestDispatcher(JSP.AUTHORIZATION_PAGE).forward(request,response);
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
            errorMessage.append(e.getMessage());
        }
        return false;
    }
}
