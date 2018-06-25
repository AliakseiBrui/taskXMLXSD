package com.epam.task4.service;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.constant.PagePath;
import com.epam.task4.entity.AnswerType;
import com.epam.task4.factory.AnswerFactory;
import com.epam.task4.factory.DeviceBuilderFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ParseService implements CommandService {

    @Override
    public void process(HashMap<String, String> parameterMap, HashMap<String, Object> attributeMap)
            throws ServletException, IOException {

        String parserType = parameterMap.get("parserType");
        DeviceBuilderFactory.DeviceBuilderType builderType = DeviceBuilderFactory.DeviceBuilderType.valueOf(parserType.toUpperCase());
        AbstractDeviceBuilder deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(builderType);
        Objects.requireNonNull(deviceBuilder).buildDeviceSet(parameterMap.get("pathToXML"));
        attributeMap.put("pcComponentSet",deviceBuilder.getPcComponentSet());
        attributeMap.put("phoneSet",deviceBuilder.getPhoneSet());
        attributeMap.put("parserType",parserType);
        attributeMap.put(ANSWER_ATTRIBUTE,AnswerFactory
                .createAnswer(AnswerType.FORWARD,PagePath.RESULT_PAGE));
    }
}
