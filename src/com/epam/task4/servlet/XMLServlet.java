package com.epam.task4.servlet;

import com.epam.task4.command.XMLCommandType;
import com.epam.task4.pool.ConnectionPool;

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
    public void init() throws ServletException {
        super.init();
        ConnectionPool.INSTANCE.init();
    }

    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.closeAll();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("commandType");
        XMLCommandType.valueOf(commandName).getCommand()
                .execute(request,response,getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter("commandType");
        XMLCommandType.valueOf(commandName).getCommand()
                .execute(request,response,getServletContext());
    }
}
