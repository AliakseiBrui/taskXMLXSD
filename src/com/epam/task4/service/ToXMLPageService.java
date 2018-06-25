package com.epam.task4.service;

import com.epam.task4.constant.PagePath;
import com.epam.task4.entity.Answer;
import com.epam.task4.entity.AnswerType;
import com.epam.task4.factory.AnswerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

public class ToXMLPageService implements CommandService {
    @Override
    public void process(HashMap<String, String> parameterMap, HashMap<String, Object> attributeMap)
            throws ServletException, IOException {

        attributeMap.put(ANSWER_ATTRIBUTE,AnswerFactory
                .createAnswer(AnswerType.REDIRECT,PagePath.XML_PAGE));
    }
}
