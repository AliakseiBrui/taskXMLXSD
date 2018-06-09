package com.epam.task4.factory;

import com.epam.task4.builder.AbstractDeviceBuilder;
import com.epam.task4.builder.DeviceDOMBuilder;
import com.epam.task4.builder.DeviceSAXBuilder;
import com.epam.task4.builder.DeviceStAXBuilder;

public class DeviceBuilderFactory {
    public enum DeviceBuilderType{
        SAX,STAX,DOM
    }

    private DeviceBuilderFactory(){
    }

    public static AbstractDeviceBuilder createDeviceBuilder(DeviceBuilderType builderType){

        switch (builderType){
            case DOM:
                return new DeviceDOMBuilder();
            case STAX:
                return new DeviceStAXBuilder();
            case SAX:
                return new DeviceSAXBuilder();

                default: return null;
        }
    }
}
