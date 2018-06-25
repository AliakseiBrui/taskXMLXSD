package com.epam.task4.command;

import com.epam.task4.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum XMLCommandType {
    TO_MAIN_PAGE_COMMAND(new ToAnyPageCommand(new ToMainPageService())),
    PARSE_COMMAND(new ParseCommand(new ParseService())),
    AUTHORIZATION_COMMAND(new AuthorizationCommand(new AuthorizationService())),
    REGISTRATION_COMMAND(new RegistrationCommand(new RegistrationService())),
    TO_REGISTRATION_PAGE_COMMAND(new ToAnyPageCommand(new ToRegistrationPageService())),
    TO_AUTHORIZATION_PAGE_COMMAND(new ToAnyPageCommand(new ToAuthorizationPageService())),
    LOG_OUT_COMMAND(new LogOutCommand(new LogOutService())),
    TO_XML_PAGE_COMMAND(new ToAnyPageCommand(new ToXMLPageService()));

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
