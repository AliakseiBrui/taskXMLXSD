package com.epam.task4.request;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.factory.DeviceBuilderFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestHandler {
    private static final String XML_FILE_PATH = "someDevices.xml";

    public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        RequestType requestType = RequestType.valueOf(request.getParameter("requestType"));

        switch (requestType){
            case PARSE:
                String parserType = request.getParameter("parserType");
                DeviceBuilderFactory.DeviceBuilderType builderType = DeviceBuilderFactory.DeviceBuilderType.valueOf(parserType.toUpperCase());
                AbstractDeviceBuilder deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(builderType);
                deviceBuilder.buildDeviceSet(servletContext.getRealPath("/res/")+XML_FILE_PATH);
                request.setAttribute("deviceSet",deviceBuilder.getDeviceSet());
                request.setAttribute("parserType",parserType);
                request.getRequestDispatcher("jsp/result.jsp").forward(request,response);
                break;
            case TO_MAIN_PAGE:
                request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
