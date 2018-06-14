package com.epam.task4.commandhandler;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.factory.DeviceBuilderFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class ParseCommandHandler implements CommandHandler {
    private static final String XML_FILE_PATH = "someDevices.xml";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        String parserType = request.getParameter("parserType");
        DeviceBuilderFactory.DeviceBuilderType builderType = DeviceBuilderFactory.DeviceBuilderType.valueOf(parserType.toUpperCase());
        AbstractDeviceBuilder deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(builderType);
        Objects.requireNonNull(deviceBuilder).buildDeviceSet(servletContext.getRealPath("/res/")+XML_FILE_PATH);
        request.setAttribute("pcComponentSet",deviceBuilder.getPcComponentSet());
        request.setAttribute("phoneSet",deviceBuilder.getPhoneSet());
        request.setAttribute("parserType",parserType);
        request.getRequestDispatcher("jsp/result.jsp").forward(request,response);
    }
}
