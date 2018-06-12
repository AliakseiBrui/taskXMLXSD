package com.epam.task4.command;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.factory.DeviceBuilderFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RequestHandler implements Handler {
    private static final String XML_FILE_PATH = "someDevices.xml";

    @Override
    public void handle(XMLCommandType commandType, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {

        switch (commandType){
            case PARSE_COMMAND:
                String parserType = request.getParameter("parserType");
                DeviceBuilderFactory.DeviceBuilderType builderType = DeviceBuilderFactory.DeviceBuilderType.valueOf(parserType.toUpperCase());
                AbstractDeviceBuilder deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(builderType);
                Objects.requireNonNull(deviceBuilder).buildDeviceSet(servletContext.getRealPath("/res/")+XML_FILE_PATH);
                request.setAttribute("pcComponentSet",deviceBuilder.getPcComponentSet());
                request.setAttribute("phoneSet",deviceBuilder.getPhoneSet());
                request.setAttribute("parserType",parserType);
                request.getRequestDispatcher("jsp/result.jsp").forward(request,response);
                break;
            case TO_MAIN_PAGE_COMMAND:
                request.getRequestDispatcher("index.jsp").forward(request,response);
                break;
            case AUTHORIZATION_COMMAND:
                break;
        }
    }
}
