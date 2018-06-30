package com.epam.task4.factory;

import com.epam.task4.entity.Router;

public class RouterFactory {

    private RouterFactory(){}

    public static Router createRouter(Router.RouteType routeType, String answerPage){

        return new Router(routeType,answerPage);
    }
}
