package com.epam.task4.servlet;

import com.epam.task4.command.XMLCommandType;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="XMLParsingServlet",
            urlPatterns = "/XMLServlet")
public class XMLServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String commandName = request.getParameter(ParameterConstant.COMMAND_TYPE_PARAMETER);
        Router router = XMLCommandType.valueOf(commandName).getCommand().
                execute(request);

        switch (router.getRouteType()){
            case FORWARD:
                request.getRequestDispatcher(router.getRoutePage()).forward(request,response);
                break;
            case REDIRECT:
                response.sendRedirect(router.getRoutePage());
                break;
            default:
        }
    }

}
