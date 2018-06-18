package com.epam.task4.command;

import com.epam.task4.commandhandler.*;

public enum XMLCommandType {
    TO_MAIN_PAGE_COMMAND(new ToMainPageCommandHandler()),
    PARSE_COMMAND(new ParseCommandHandler()),
    AUTHORIZATION_COMMAND(new AuthorizationCommandHandler()),
    REGISTRATION_COMMAND(new RegistrationCommandHandler()),
    TO_REGISTRATION_PAGE_COMMAND(new ToRegistrationPageCommandHandler()),
    LOG_OUT_COMMAND(new LogOutCommandHandler());

    private CommandHandler handler;

    XMLCommandType(CommandHandler handler){
        this.handler = handler;
    }

    public XMLCommand getCommand(){

        return new XMLCommand(handler);
    }
}
