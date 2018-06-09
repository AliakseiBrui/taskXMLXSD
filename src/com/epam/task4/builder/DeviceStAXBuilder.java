package com.epam.task4.builder;

import com.epam.task4.entity.Device;
import com.epam.task4.entity.DeviceEnum;
import com.epam.task4.entity.DeviceType;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public class DeviceStAXBuilder extends AbstractDeviceBuilder {
    private XMLInputFactory inputFactory;

    public DeviceStAXBuilder(){
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildDeviceSet(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;

        try{
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()){

                int type = reader.next();

                if(type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();

                    if(DeviceEnum.DEVICE.getValue().equals(name)){
                        Device device = buildDevice(reader);
                        deviceSet.add(device);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Device buildDevice(XMLStreamReader reader) throws XMLStreamException{
        Device device = new Device();
        device.setDeviceId(reader.getAttributeValue(null,DeviceEnum.ID.getValue()));
        device.setDeviceName(reader.getAttributeValue(null,DeviceEnum.NAME.getValue()));
        String name;

        while (reader.hasNext()){
            int type = reader.next();

            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (DeviceEnum.checkValue(name)){
                        case ORIGIN_COUNTRY:
                            device.setOriginCountry(getXMLText(reader));
                            break;
                        case PRICE:
                            device.setDevicePrice(BigDecimal.valueOf(Long.parseLong(getXMLText(reader))));
                            break;
                        case CRITICAL:
                            device.setCritical(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case DEVICE_TYPE:
                            device.setDeviceType(getXMLType(reader));
                            break;
                    }
                    break;

                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();

                        if(DeviceEnum.DEVICE.getValue().equals(name)){
                            return device;
                        }
            }
        }
        throw new XMLStreamException("Unknown element in tag device");
    }

    private DeviceType getXMLType(XMLStreamReader reader) throws XMLStreamException{
        DeviceType deviceType = new DeviceType();
        int type;
        String name;

        while (reader.hasNext()){
            type=reader.next();

            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();

                    switch (DeviceEnum.checkValue(name)){
                        case PERIPHERAL:
                            deviceType.setPeripheral(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case ENERGY_CONSUMPTION:
                            deviceType.setEnergyConsumption(Integer.parseInt(getXMLText(reader)));
                            break;
                        case HAS_COOLER:
                            deviceType.setHasCooler(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case DEVICE_GROUP:
                            deviceType.setDeviceGroup(DeviceType.DeviceGroup.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case PORT:
                            deviceType.setPort(DeviceType.Port.valueOf(getXMLText(reader).toUpperCase()));
                    }
                    break;

                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();

                        if(DeviceEnum.DEVICE_TYPE.getValue().equals(name)){
                            return deviceType;
                        }
            }
        }
        throw new XMLStreamException("Unknown element in tag device-type");
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
