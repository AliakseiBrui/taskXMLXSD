package com.epam.task4.builder;

import com.epam.task4.entity.ComponentType;
import com.epam.task4.entity.PCComponent;
import com.epam.task4.entity.DeviceEnum;
import com.epam.task4.entity.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;

public class DeviceStAXBuilder extends AbstractDeviceBuilder {
    private XMLInputFactory inputFactory;
    private static final Logger LOGGER = LogManager.getLogger(DeviceStAXBuilder.class);

    public DeviceStAXBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildDeviceSet(String fileName) {
        LOGGER.debug("Building device set.");
        String name;
        XMLStreamReader reader;


        try(FileInputStream inputStream = new FileInputStream(fileName)){

            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()){

                int type = reader.next();

                if(type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();

                    if(DeviceEnum.PC_COMPONENT.getTag().equals(name)){
                        PCComponent pcComponent = buildComponent(reader);
                        pcComponentSet.add(pcComponent);
                    }else if(DeviceEnum.PHONE.getTag().equals(name)){
                        Phone phone = buildPhone(reader);
                        phoneSet.add(phone);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            LOGGER.fatal("Exception while parsing devices.",e);
            throw new RuntimeException(e);
        }
    }

    private PCComponent buildComponent(XMLStreamReader reader) throws XMLStreamException{
        PCComponent pcComponent = new PCComponent();
        pcComponent.setDeviceId(reader.getAttributeValue(null,DeviceEnum.ID.getTag()));
        pcComponent.setDeviceName(reader.getAttributeValue(null,DeviceEnum.NAME.getTag()));
        String name;

        while (reader.hasNext()){
            int type = reader.next();

            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();

                switch (DeviceEnum.checkTag(name)) {
                    case ORIGIN_COUNTRY:
                        pcComponent.setOriginCountry(getXMLText(reader));
                        break;
                    case PRICE:
                        pcComponent.setDevicePrice(BigDecimal.valueOf(Long.parseLong(getXMLText(reader))));
                        break;
                    case CRITICAL:
                        pcComponent.setCritical(Boolean.parseBoolean(getXMLText(reader)));
                        break;
                    case COMPONENT_TYPE:
                        pcComponent.setComponentType(getXMLType(reader));
                        break;
                    default:
                }

            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();

                if (DeviceEnum.PC_COMPONENT.getTag().equals(name)) {
                    return pcComponent;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag pc-component");
    }

    private Phone buildPhone(XMLStreamReader reader) throws XMLStreamException{
        Phone phone = new Phone();
        phone.setDeviceId(reader.getAttributeValue(null,DeviceEnum.ID.getTag()));
        phone.setDeviceName(reader.getAttributeValue(null,DeviceEnum.NAME.getTag()));
        String name;

        while (reader.hasNext()){
            int type = reader.next();

            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();

                switch (DeviceEnum.checkTag(name)) {
                    case ORIGIN_COUNTRY:
                        phone.setOriginCountry(getXMLText(reader));
                        break;
                    case PRICE:
                        phone.setDevicePrice(BigDecimal.valueOf(Long.parseLong(getXMLText(reader))));
                        break;
                    case RAM:
                        phone.setRam(Integer.parseInt(getXMLText(reader)));
                        break;
                    case BUILD_DATE:
                        phone.setBuildDate(Date.valueOf(getXMLText(reader)));
                        break;
                    default:
                }

            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();

                if (DeviceEnum.PHONE.getTag().equals(name)) {
                    return phone;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag phone");
    }

    private ComponentType getXMLType(XMLStreamReader reader) throws XMLStreamException{
        ComponentType componentType = new ComponentType();
        int type;
        String name;

        while (reader.hasNext()){
            type=reader.next();

            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();

                switch (DeviceEnum.checkTag(name)) {
                    case PERIPHERAL:
                        componentType.setPeripheral(Boolean.parseBoolean(getXMLText(reader)));
                        break;
                    case ENERGY_CONSUMPTION:
                        componentType.setEnergyConsumption(Integer.parseInt(getXMLText(reader)));
                        break;
                    case HAS_COOLER:
                        componentType.setHasCooler(Boolean.parseBoolean(getXMLText(reader)));
                        break;
                    case COMPONENT_GROUP:
                        componentType.setComponentGroup(ComponentType.ComponentGroup.valueOf(getXMLText(reader).toUpperCase()));
                        break;
                    case PORT:
                        componentType.setPort(ComponentType.Port.valueOf(getXMLText(reader).toUpperCase()));
                        break;
                    default:
                }

            } else if (type == XMLStreamConstants.END_ELEMENT) {
                name = reader.getLocalName();

                if (DeviceEnum.COMPONENT_TYPE.getTag().equals(name)) {
                    return componentType;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag component-type");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text="";

        if(reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
