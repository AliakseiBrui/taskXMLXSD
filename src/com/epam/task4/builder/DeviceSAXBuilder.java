package com.epam.task4.builder;

import com.epam.task4.parsing.DeviceHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class DeviceSAXBuilder extends AbstractDeviceBuilder {
    private DeviceHandler deviceHandler;
    private XMLReader xmlReader;

    public DeviceSAXBuilder(){
        deviceHandler = new DeviceHandler();

        try{
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(deviceHandler);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buildDeviceSet(String fileName) {

        try{
            xmlReader.parse(fileName);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        deviceSet=deviceHandler.getDeviceSet();
    }
}
