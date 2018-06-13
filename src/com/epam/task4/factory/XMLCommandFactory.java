package com.epam.task4.factory;

import com.epam.task4.command.*;

public class XMLCommandFactory {

    private XMLCommandFactory(){}

    public static XMLCommand createXMLCommand(XMLCommandType commandType){
        XMLCommand command = null;

        switch (commandType){
            case TO_MAIN_PAGE_COMMAND:
                command = new ToMainPageXMLCommand(new RequestHandler());
                break;
            case PARSE_COMMAND:
                command = new ParseXMLCommand(new RequestHandler());
                break;
            case AUTHORIZATION_COMMAND:
                command = new AuthorizationCommand(new RequestHandler());
                break;
            case REGISTRATION_COMMAND:
                command = new RegistrationCommand(new RequestHandler());
                break;
            case TO_REGISTRATION_PAGE_COMMAND:
                command = new ToRegistrationPageCommand(new RequestHandler());
                break;
            case SIGN_OUT_COMMAND:
                command = new SignOutCommand(new RequestHandler());
                break;
        }
        return command;
    }
}
