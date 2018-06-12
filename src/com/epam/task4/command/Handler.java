package com.epam.task4.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Handler {

    void handle(XMLCommandType commandType, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException;
}
