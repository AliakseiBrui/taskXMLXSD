package com.epam.task4.parsing;

import com.epam.task4.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {
    private Set<PCComponent> pcComponentSet;
    private Set<Phone> phoneSet;
    private Device current = null;
    private DeviceEnum currentEnum = null;
    private EnumSet<DeviceEnum> withText;
    public DeviceHandler(){
        pcComponentSet = new HashSet<>();
        phoneSet = new HashSet<>();
        withText = EnumSet.range(DeviceEnum.ORIGIN_COUNTRY,DeviceEnum.ENERGY_CONSUMPTION);
    }

    public Set<PCComponent> getPcComponentSet(){
        return pcComponentSet;
    }


    public Set<Phone> getPhoneSet() {
        return phoneSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){

        if(DeviceEnum.PC_COMPONENT.getTag().equals(qName)){
            current = new PCComponent();
            current.setDeviceId(attributes.getValue(0));
            current.setDeviceName(attributes.getValue(1));
        }else if(DeviceEnum.PHONE.getTag().equals(qName)){
            current = new Phone();
            current.setDeviceId(attributes.getValue(0));
            current.setDeviceName(attributes.getValue(1));
        } else{
            DeviceEnum temp = DeviceEnum.checkTag(qName);

            if(withText.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){

        if(DeviceEnum.PC_COMPONENT.getTag().equals(qName)){
            pcComponentSet.add((PCComponent)current);
        }else if(DeviceEnum.PHONE.getTag().equals(qName)){
            phoneSet.add((Phone)current);
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
                    ((PCComponent) current).setCritical(Boolean.parseBoolean(s));
                    break;
                case PERIPHERAL:
                    ((PCComponent) current).getComponentType().setPeripheral(Boolean.parseBoolean(s));
                    break;
                case ENERGY_CONSUMPTION:
                    ((PCComponent) current).getComponentType().setEnergyConsumption(Integer.parseInt(s));
                    break;
                case HAS_COOLER:
                    ((PCComponent) current).getComponentType().setHasCooler(Boolean.parseBoolean(s));
                    break;
                case COMPONENT_GROUP:
                    ((PCComponent) current).getComponentType().setComponentGroup(ComponentType.ComponentGroup.valueOf(s.toUpperCase()));
                    break;
                case PORT:
                    ((PCComponent) current).getComponentType().setPort(ComponentType.Port.valueOf(s.toUpperCase()));
                    break;
                case RAM:
                    ((Phone) current).setRam(Integer.parseInt(s));
                    break;
                case BUILD_DATE:
                    ((Phone) current).setBuildDate(Date.valueOf(s));
            }
        }
        currentEnum = null;
    }

}
