package com.epam.task4.service;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public interface CommandService {

    void process(Map<String, String> parameterMap, Map<String, Object> attributeMap)
            throws ServletException, IOException;
}
