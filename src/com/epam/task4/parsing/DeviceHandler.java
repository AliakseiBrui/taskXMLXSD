package com.epam.task4.parsing;

import com.epam.task4.entity.Device;
import com.epam.task4.entity.DeviceEnum;
import com.epam.task4.entity.DeviceType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {
    private Set<Device> deviceSet;
    private Device current = null;
    private DeviceEnum currentEnum = null;
    private EnumSet<DeviceEnum> withText;
    private static final String DEVICE_TAG = "device";

    public DeviceHandler(){
        deviceSet = new HashSet<>();
        withText = EnumSet.range(DeviceEnum.ORIGIN_COUNTRY,DeviceEnum.ENERGY_CONSUMPTION);
    }

    public Set<Device> getDeviceSet(){
        return deviceSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){

        if(DEVICE_TAG.equals(localName)){
            current = new Device();
            current.setDeviceId(attributes.getValue(0));
            current.setDeviceName(attributes.getValue(1));
        }else{
            DeviceEnum temp = DeviceEnum.checkValue(localName);

            if(withText.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){

        if(DEVICE_TAG.equals(localName)){
            deviceSet.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        String s = new String(ch,start,length).trim();

        if(currentEnum!=null){

            switch (currentEnum){
                case ORIGIN_COUNTRY:
                    current.setOriginCountry(s);
                    break;
                case PRICE:
                    current.setDevicePrice(BigDecimal.valueOf(Long.parseLong(s)));
                    break;
                case CRITICAL:
                    current.setCritical(Boolean.parseBoolean(s));
                    break;
                case PERIPHERAL:
                    current.getDeviceType().setPeripheral(Boolean.parseBoolean(s));
                    break;
                case ENERGY_CONSUMPTION:
                    current.getDeviceType().setEnergyConsumption(Integer.parseInt(s));
                    break;
                case HAS_COOLER:
                    current.getDeviceType().setHasCooler(Boolean.parseBoolean(s));
                    break;
                case DEVICE_GROUP:
                    current.getDeviceType().setDeviceGroup(DeviceType.DeviceGroup.valueOf(s.toUpperCase()));
                    break;
                case PORT:
                    current.getDeviceType().setPort(DeviceType.Port.valueOf(s.toUpperCase()));
                    break;

                    default:
                        throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(),currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
