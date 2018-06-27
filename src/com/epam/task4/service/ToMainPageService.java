package com.epam.task4.service;

import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.PagePath;
import com.epam.task4.entity.AnswerType;
import com.epam.task4.factory.AnswerFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public class ToMainPageService implements CommandService {
    @Override
    public void process(Map<String, String> parameterMap, Map<String, Object> attributeMap)
            throws ServletException, IOException {

        attributeMap.put(AttributeConstant.ANSWER_ATTRIBUTE,AnswerFactory
                .createAnswer(AnswerType.REDIRECT,PagePath.MAIN_PAGE));
    }
}
