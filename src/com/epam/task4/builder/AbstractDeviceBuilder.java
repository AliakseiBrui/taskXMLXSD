package com.epam.task4.builder;

import com.epam.task4.entity.Device;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeviceBuilder {
    protected Set<Device> deviceSet;

    public AbstractDeviceBuilder(){
        deviceSet = new HashSet<>();
    }

    public AbstractDeviceBuilder(Set<Device> deviceSet){
        this.deviceSet = deviceSet;
    }

    public Set<Device> getDeviceSet(){
        return deviceSet;
    }

    abstract public void buildDeviceSet(String fileName);
}
