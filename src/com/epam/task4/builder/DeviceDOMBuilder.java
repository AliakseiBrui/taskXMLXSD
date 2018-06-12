package com.epam.task4.builder;

import com.epam.task4.entity.DeviceEnum;
import com.epam.task4.entity.PCComponent;
import com.epam.task4.entity.ComponentType;
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
            NodeList deviceList = root.getElementsByTagName(DeviceEnum.PC_COMPONENT.getTag());

            for(int i = 0; i < deviceList.getLength(); i++){
                Element deviceElement = (Element) deviceList.item(i);
                PCComponent pcComponent = buildDevice(deviceElement);
                pcComponentSet.add(pcComponent);
            }
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PCComponent buildDevice(Element deviceElement){
        PCComponent pcComponent = new PCComponent();
        pcComponent.setDeviceId(deviceElement.getAttribute(DeviceEnum.ID.getTag()));
        pcComponent.setDeviceName(deviceElement.getAttribute(DeviceEnum.NAME.getTag()));
        pcComponent.setOriginCountry(getElementTextContent(deviceElement,DeviceEnum.ORIGIN_COUNTRY.getTag()));
        pcComponent.setDevicePrice(BigDecimal.valueOf(Long.parseLong(getElementTextContent(deviceElement,DeviceEnum.PRICE.getTag()))));
        pcComponent.setCritical(Boolean.parseBoolean(getElementTextContent(deviceElement,DeviceEnum.CRITICAL.getTag())));
        ComponentType componentType = pcComponent.getComponentType();
        Element typeElement = (Element) deviceElement.getElementsByTagName(DeviceEnum.COMPONENT_TYPE.getTag()).item(0);
        componentType.setComponentGroup(ComponentType.ComponentGroup.valueOf(getElementTextContent(typeElement,DeviceEnum.COMPONENT_GROUP.getTag()).toUpperCase()));
        componentType.setEnergyConsumption(Integer.parseInt(getElementTextContent(typeElement,DeviceEnum.ENERGY_CONSUMPTION.getTag())));
        componentType.setHasCooler(Boolean.parseBoolean(getElementTextContent(typeElement,DeviceEnum.HAS_COOLER.getTag())));
        componentType.setPeripheral(Boolean.parseBoolean(getElementTextContent(typeElement,DeviceEnum.HAS_COOLER.getTag())));
        componentType.setPort(ComponentType.Port.valueOf(getElementTextContent(typeElement,DeviceEnum.PORT.getTag()).toUpperCase()));

        return pcComponent;
    }

    private static String getElementTextContent(Element element, String elementName){
        return element.getElementsByTagName(elementName).item(0).getTextContent();
    }
}
