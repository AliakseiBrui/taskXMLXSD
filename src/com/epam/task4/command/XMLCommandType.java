package com.epam.task4.command;

import com.epam.task4.commandhandler.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum XMLCommandType {
    TO_MAIN_PAGE_COMMAND(new XMLCommand(new ToMainPageCommandHandler())),
    PARSE_COMMAND(new XMLCommand(new ParseCommandHandler())),
    AUTHORIZATION_COMMAND(new XMLCommand(new AuthorizationCommandHandler())),
    REGISTRATION_COMMAND(new XMLCommand(new RegistrationCommandHandler())),
    TO_REGISTRATION_PAGE_COMMAND(new XMLCommand(new ToRegistrationPageCommandHandler())),
    TO_AUTHORIZATION_PAGE_COMMAND(new XMLCommand(new ToAuthorizationPageCommandHandler())),
    LOG_OUT_COMMAND(new XMLCommand(new LogOutCommandHandler()));

    private XMLCommand command;
    private static final Logger LOGGER = LogManager.getLogger(XMLCommandType.class);

    XMLCommandType(XMLCommand command){
        this.command = command;
    }

    public XMLCommand getCommand(){
        LOGGER.debug("Getting command " + this + ".");
        return command;
    }
}
