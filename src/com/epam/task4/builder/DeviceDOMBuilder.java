package com.epam.task4.builder;

import com.epam.task4.entity.Device;
import com.epam.task4.entity.DeviceType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;

public class DeviceDOMBuilder extends AbstractDeviceBuilder {
    private DocumentBuilder documentBuilder;

    public DeviceDOMBuilder(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try{
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void buildDeviceSet(String fileName) {
        Document document;

        try{
            document=documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList deviceList = root.getElementsByTagName("device");

            for(int i = 0; i < deviceList.getLength(); i++){
                Element deviceElement = (Element) deviceList.item(i);
                Device device = buildDevice(deviceElement);
                deviceSet.add(device);
            }
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Device buildDevice(Element deviceElement){
        Device device = new Device();
        device.setDeviceId(deviceElement.getAttribute("id"));
        device.setDeviceName(deviceElement.getAttribute("name"));
        device.setOriginCountry(getElementTextContent(deviceElement,"origin-country"));
        device.setDevicePrice(BigDecimal.valueOf(Long.parseLong(getElementTextContent(deviceElement,"price"))));
        device.setCritical(Boolean.parseBoolean(getElementTextContent(deviceElement,"critical")));
        DeviceType deviceType = device.getDeviceType();
        Element typeElement = (Element) deviceElement.getElementsByTagName("device-type").item(0);
        deviceType.setDeviceGroup(DeviceType.DeviceGroup.valueOf(getElementTextContent(typeElement,"device-group").toUpperCase()));
        deviceType.setEnergyConsumption(Integer.parseInt(getElementTextContent(typeElement,"energy-consumption")));
        deviceType.setHasCooler(Boolean.parseBoolean(getElementTextContent(typeElement,"has-cooler")));
        deviceType.setPeripheral(Boolean.parseBoolean(getElementTextContent(typeElement,"peripheral")));
        deviceType.setPort(DeviceType.Port.valueOf(getElementTextContent(typeElement,"port").toUpperCase()));

        return device;
    }

    private static String getElementTextContent(Element element, String elementName){
        return element.getElementsByTagName(elementName).item(0).getTextContent();
    }
}
