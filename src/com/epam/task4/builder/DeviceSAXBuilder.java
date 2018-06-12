package com.epam.task4.builder;

import com.epam.task4.parsing.DeviceHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class DeviceSAXBuilder extends AbstractDeviceBuilder {
    private DeviceHandler deviceHandler;
    private XMLReader xmlReader;

    public DeviceSAXBuilder(){
        deviceHandler = new DeviceHandler();

        try{
            xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xmlReader.setContentHandler(deviceHandler);
        } catch (SAXException | ParserConfigurationException e) {
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
        pcComponentSet = deviceHandler.getPcComponentSet();
        phoneSet = deviceHandler.getPhoneSet();
    }
}
