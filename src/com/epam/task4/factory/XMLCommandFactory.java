package com.epam.task4.factory;

import com.epam.task4.command.*;

public class XMLCommandFactory {

    public static XMLCommand createXMLCommand(XMLCommandType commandType){

        XMLCommand command = null;

        switch (commandType){
            case TO_MAIN_PAGE_COMMAND:
                command = new ToMainPageXMLCommand(new RequestHandler());
                break;
            case PARSE_COMMAND:
                command = new ParseXMLCommand(new RequestHandler());
        }
        return command;
    }
}
