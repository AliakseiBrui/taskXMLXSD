package com.epam.task4.commandhandler;

import com.epam.task4.constant.JSP;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationPageCommandHandler implements CommandHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        request.getRequestDispatcher(JSP.REGISTRATION_PAGE).forward(request,response);
    }
}
