package com.epam.task4.factory;

import com.epam.task4.command.*;
import com.epam.task4.commandhandler.*;

public class XMLCommandFactory {

    private XMLCommandFactory(){}

    public static XMLCommand createXMLCommand(XMLCommandType commandType){
        XMLCommand command = new XMLCommand();

        switch (commandType){
            case TO_MAIN_PAGE_COMMAND:
                command.setHandler(new ToMainPageCommandHandler());
                break;
            case PARSE_COMMAND:
                command.setHandler(new ParseCommandHandler());
                break;
            case AUTHORIZATION_COMMAND:
                command.setHandler(new AuthorizationCommandHandler());
                break;
            case REGISTRATION_COMMAND:
                command.setHandler(new RegistrationCommandHandler());
                break;
            case TO_REGISTRATION_PAGE_COMMAND:
                command.setHandler(new ToRegistrationPageCommandHandler());
                break;
            case LOG_OUT_COMMAND:
                command.setHandler(new LogOutCommandHandler());
                break;
        }
        return command;
    }
}
