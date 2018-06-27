package com.epam.task4.servlet;

import com.epam.task4.command.XMLCommandType;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Answer;

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

        String commandName = request.getParameter(ParameterConstant.PARSER_TYPE_PARAMETER);
        Answer answer = XMLCommandType.valueOf(commandName).getCommand().
                execute(request,response,getServletContext());

        switch (answer.getAnswerType()){
            case FORWARD:
                request.getRequestDispatcher(answer.getAnswerPage()).forward(request,response);
                break;
            case REDIRECT:
                response.sendRedirect(answer.getAnswerPage());
                break;
            default:
        }
    }

}
