package com.test.task4.builder;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.constant.FilePath;
import com.epam.task4.factory.DeviceBuilderFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class DeviceXMLBuilderTest {
    private AbstractDeviceBuilder deviceBuilder;
    @Test
    public void testBuildDevicesDOM1(){
        deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(DeviceBuilderFactory.DeviceBuilderType.DOM);
        deviceBuilder.buildDeviceSet(FilePath.WEB_DIRECTORY + File.separator + FilePath.RESOURCE_DIRECTORY + File.separator + FilePath.XML_FILE);
        Assert.assertNotNull(deviceBuilder.getPhoneSet());
        Assert.assertNotNull(deviceBuilder.getPcComponentSet());
    }

    @Test
    public void testBuildDevicesSAX1(){
        deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(DeviceBuilderFactory.DeviceBuilderType.SAX);
        deviceBuilder.buildDeviceSet(FilePath.WEB_DIRECTORY + File.separator + FilePath.RESOURCE_DIRECTORY + File.separator + FilePath.XML_FILE);
        Assert.assertNotNull(deviceBuilder.getPhoneSet());
        Assert.assertNotNull(deviceBuilder.getPcComponentSet());
    }

    @Test
    public void testBuildDevicesStAX(){
        deviceBuilder = DeviceBuilderFactory.createDeviceBuilder(DeviceBuilderFactory.DeviceBuilderType.STAX);
        deviceBuilder.buildDeviceSet(FilePath.WEB_DIRECTORY + File.separator + FilePath.RESOURCE_DIRECTORY + File.separator + FilePath.XML_FILE);
        Assert.assertNotNull(deviceBuilder.getPhoneSet());
        Assert.assertNotNull(deviceBuilder.getPcComponentSet());
    }
}
