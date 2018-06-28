package com.epam.task4.entity;

public class Router {
    public enum RouteType {
        FORWARD,
        REDIRECT
    }

    private RouteType routeType;
    private String routePage;

    public Router(RouteType routeType, String routePage) {
        this.routeType = routeType;
        this.routePage = routePage;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public String getRoutePage() {
        return routePage;
    }

    public void setRoutePage(String routePage) {
        this.routePage = routePage;
    }
}
