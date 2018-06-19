package com.epam.task4.builder;

import com.epam.task4.entity.DeviceEnum;
import com.epam.task4.entity.PCComponent;
import com.epam.task4.entity.ComponentType;
import com.epam.task4.entity.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

public class DeviceDOMBuilder extends AbstractDeviceBuilder {
    private DocumentBuilder documentBuilder;
    private static final Logger LOGGER  = LogManager.getLogger(DeviceDOMBuilder.class);

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
        LOGGER.debug("Building device set.");
        Document document;

        try{
            document=documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList componentList = root.getElementsByTagName(DeviceEnum.PC_COMPONENT.getTag());
            NodeList phoneList = root.getElementsByTagName(DeviceEnum.PHONE.getTag());

            for(int i = 0; i < componentList.getLength(); i++){
                Element componentElement = (Element) componentList.item(i);
                PCComponent pcComponent = buildComponent(componentElement);
                pcComponentSet.add(pcComponent);
            }

            for(int i=0; i < phoneList.getLength(); i++){
                Element phoneElement = (Element) phoneList.item(i);
                Phone phone = buildPhone(phoneElement);
                phoneSet.add(phone);
            }
        } catch (SAXException | IOException e) {
            LOGGER.error("Exception while parsing devices.",e);
            throw new RuntimeException(e);
        }
    }

    private PCComponent buildComponent(Element deviceElement){
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

    private Phone buildPhone(Element phoneElement){
        Phone phone = new Phone();
        phone.setDeviceId(phoneElement.getAttribute(DeviceEnum.ID.getTag()));
        phone.setDeviceName(phoneElement.getAttribute(DeviceEnum.NAME.getTag()));
        phone.setOriginCountry(getElementTextContent(phoneElement,DeviceEnum.ORIGIN_COUNTRY.getTag()));
        phone.setDevicePrice(BigDecimal.valueOf(Long.parseLong(getElementTextContent(phoneElement,DeviceEnum.PRICE.getTag()))));
        phone.setRam(Integer.parseInt(getElementTextContent(phoneElement,DeviceEnum.RAM.getTag())));
        phone.setBuildDate(Date.valueOf(getElementTextContent(phoneElement,DeviceEnum.BUILD_DATE.getTag())));

        return phone;
    }

    private static String getElementTextContent(Element element, String elementName){
        return element.getElementsByTagName(elementName).item(0).getTextContent();
    }
}
