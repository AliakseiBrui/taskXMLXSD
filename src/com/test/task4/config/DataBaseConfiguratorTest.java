package com.test.task4.config;

import com.epam.task4.config.DataBaseConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

public class DataBaseConfiguratorTest {

    @Test
    public void testConfig(){

        DataBaseConfigurator.INSTANCE.configureDBProperties("web/config/db_config.properties");

        Properties properties = DataBaseConfigurator.INSTANCE.getDbProperties();

        Assert.assertNotNull(properties);

        System.out.println("url: "+properties.get("url"));
        System.out.println("user: "+properties.get("user"));
        System.out.println("password: "+properties.get("password"));
        System.out.println("autoReconnect: "+properties.get("autoReconnect"));
        System.out.println("characterEncoding: "+properties.get("characterEncoding"));
        System.out.println("useUnicode: "+properties.get("useUnicode"));
        System.out.println("useSSL: "+properties.get("useSSL"));

    }
}
