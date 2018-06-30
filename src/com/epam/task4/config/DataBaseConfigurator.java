package com.epam.task4.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

public enum  DataBaseConfigurator {
    INSTANCE;

    private Properties dbProperties = null;
    private static final Logger LOGGER = LogManager.getLogger(DataBaseConfigurator.class);

    public void configureDBProperties(String filePath){
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(new File(filePath));
            dbProperties = new Properties();
            dbProperties.load(inputStream);
        } catch (FileNotFoundException e) {
            LOGGER.fatal("Database config file was not found.",e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGGER.fatal("IOException while loading database properties file.",e);
            throw new RuntimeException(e);
        }
    }

    public Properties getDbProperties(){
        return dbProperties;
    }
}
