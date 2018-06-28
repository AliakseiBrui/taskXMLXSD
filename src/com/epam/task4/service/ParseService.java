package com.epam.task4.service;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.constant.AttributeConstant;
import com.epam.task4.constant.PagePath;
import com.epam.task4.constant.ParameterConstant;
import com.epam.task4.entity.Router;
import com.epam.task4.factory.RouterFactory;
import com.epam.task4.factory.DeviceBuilderFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ParseService implements CommandService {

    @Override
    public void process(Map<String, String> parameterMap, Map<String, Object> attributeMap)
            throws ServletException, IOException {

        String parserType = parameterMap.get(ParameterConstant.PARSER_TYPE_PARAMETER);
        DeviceBuilderFactory.DeviceBuilderType builderType = DeviceBuilderFactory.DeviceBuilderType.valueOf(parserType.toUpperCase());
        AbstractDeviceBuilder deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(builderType);
        Objects.requireNonNull(deviceBuilder).buildDeviceSet(parameterMap.get(ParameterConstant.PATH_TO_XML_PARAMETER));
        attributeMap.put(AttributeConstant.PC_COMPONENT_SET_ATTRIBUTE,deviceBuilder.getPcComponentSet());
        attributeMap.put(AttributeConstant.PHONE_SET_ATTRIBUTE,deviceBuilder.getPhoneSet());
        attributeMap.put(AttributeConstant.PARSER_TYPE_ATTRIBUTE,parserType);
        attributeMap.put(AttributeConstant.ROUTER_ATTRIBUTE,RouterFactory
                .createAnswer(Router.RouteType.FORWARD,PagePath.RESULT_PAGE));
    }
}
