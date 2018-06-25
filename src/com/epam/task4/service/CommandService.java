package com.epam.task4.service;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

public interface CommandService {
    static final String ANSWER_ATTRIBUTE = "answer";
    void process(HashMap<String, String> parameterMap, HashMap<String, Object> attributeMap)
            throws ServletException, IOException;
}
