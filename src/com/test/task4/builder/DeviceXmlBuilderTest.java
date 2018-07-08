package com.test.task4.builder;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.constant.FilePath;
import com.epam.task4.factory.DeviceBuilderFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class DeviceXmlBuilderTest {
    private AbstractDeviceBuilder deviceBuilder;
    private static final String XML_FILE_PATH = FilePath.WEB_DIRECTORY + File.separator + FilePath.RESOURCE_DIRECTORY + File.separator + FilePath.XML_FILE;
    @Test
    public void testBuildDevicesDOM1(){
        deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(DeviceBuilderFactory.DeviceBuilderType.DOM);
        deviceBuilder.buildDeviceSet(XML_FILE_PATH);
        Assert.assertNotNull(deviceBuilder.getPhoneSet());
        Assert.assertNotNull(deviceBuilder.getPcComponentSet());
    }

    @Test
    public void testBuildDevicesSAX1(){
        deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(DeviceBuilderFactory.DeviceBuilderType.SAX);
        deviceBuilder.buildDeviceSet(XML_FILE_PATH);
        Assert.assertNotNull(deviceBuilder.getPhoneSet());
        Assert.assertNotNull(deviceBuilder.getPcComponentSet());
    }

    @Test
    public void testBuildDevicesStAX1(){
        deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(DeviceBuilderFactory.DeviceBuilderType.STAX);
        deviceBuilder.buildDeviceSet(XML_FILE_PATH);
        Assert.assertNotNull(deviceBuilder.getPhoneSet());
        Assert.assertNotNull(deviceBuilder.getPcComponentSet());
    }
}
