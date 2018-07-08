package com.epam.task4.factory;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.builder.DeviceDomBuilder;
import com.epam.task4.builder.DeviceSaxBuilder;
import com.epam.task4.builder.DeviceStaxBuilder;

public class DeviceBuilderFactory {
    public enum DeviceBuilderType{
        SAX,STAX,DOM
    }

    private DeviceBuilderFactory(){
    }

    public static AbstractDeviceBuilder createDeviceBuilder(DeviceBuilderType builderType){

        switch (builderType){
            case DOM:
                return new DeviceDomBuilder();
            case STAX:
                return new DeviceStaxBuilder();
            case SAX:
                return new DeviceSaxBuilder();

            default:
                throw new EnumConstantNotPresentException(builderType.getClass(),builderType.name());
        }
    }
}
