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

        if(authorize(authorizationLogin,authorizationPassword)){
            //To application page
            request.getSession().setAttribute("login",authorizationLogin);
            request.getRequestDispatcher(JSP.MAIN_PAGE).forward(request,response);
        }else{
            //To authorization page + errors
            request.setAttribute("mainPageMessage","Something was wrong");
            request.getRequestDispatcher(JSP.AUTHORIZATION_PAGE).forward(request,response);
        }
    }

    private boolean authorize(String login, String password){
        UserDAO userDAO = new UserDAO();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        try {
            User user = userDAO.findUserByLogin(login);

            if(user != null && passwordEncoder.encryptPassword(password).equals(user.getPass())){

                return true;
            }
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
