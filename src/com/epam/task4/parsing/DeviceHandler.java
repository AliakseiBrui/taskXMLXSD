package com.epam.task4.parsing;

import com.epam.task4.entity.ComponentType;
import com.epam.task4.entity.PCComponent;
import com.epam.task4.entity.DeviceEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DeviceHandler extends DefaultHandler {
    private Set<PCComponent> pcComponentSet;
    private PCComponent currentPCComponent = null;
    private DeviceEnum currentEnum = null;
    private EnumSet<DeviceEnum> withText;
    public DeviceHandler(){
        pcComponentSet = new HashSet<>();
        withText = EnumSet.range(DeviceEnum.ORIGIN_COUNTRY,DeviceEnum.ENERGY_CONSUMPTION);
    }

    public Set<PCComponent> getPcComponentSet(){
        return pcComponentSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){

        if(DeviceEnum.PC_COMPONENT.getTag().equals(qName)){
            currentPCComponent = new PCComponent();
            currentPCComponent.setDeviceId(attributes.getValue(0));
            currentPCComponent.setDeviceName(attributes.getValue(1));
        }else{
            DeviceEnum temp = DeviceEnum.checkTag(qName);

            if(withText.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){

        if(DeviceEnum.PC_COMPONENT.getTag().equals(qName)){
            pcComponentSet.add(currentPCComponent);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        String s = new String(ch,start,length).trim();

        if(currentEnum!=null){

            switch (currentEnum){
                case ORIGIN_COUNTRY:
                    currentPCComponent.setOriginCountry(s);
                    break;
                case PRICE:
                    currentPCComponent.setDevicePrice(BigDecimal.valueOf(Long.parseLong(s)));
                    break;
                case CRITICAL:
                    currentPCComponent.setCritical(Boolean.parseBoolean(s));
                    break;
                case PERIPHERAL:
                    currentPCComponent.getComponentType().setPeripheral(Boolean.parseBoolean(s));
                    break;
                case ENERGY_CONSUMPTION:
                    currentPCComponent.getComponentType().setEnergyConsumption(Integer.parseInt(s));
                    break;
                case HAS_COOLER:
                    currentPCComponent.getComponentType().setHasCooler(Boolean.parseBoolean(s));
                    break;
                case COMPONENT_GROUP:
                    currentPCComponent.getComponentType().setComponentGroup(ComponentType.ComponentGroup.valueOf(s.toUpperCase()));
                    break;
                case PORT:
                    currentPCComponent.getComponentType().setPort(ComponentType.Port.valueOf(s.toUpperCase()));
                    break;
            }
        }
        currentEnum = null;
    }
}
