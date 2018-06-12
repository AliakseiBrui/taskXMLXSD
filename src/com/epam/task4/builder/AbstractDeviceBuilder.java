package com.epam.task4.builder;

import com.epam.task4.entity.PCComponent;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeviceBuilder {
    protected Set<PCComponent> pcComponentSet;

    public AbstractDeviceBuilder(){
        pcComponentSet = new HashSet<>();
    }

    public AbstractDeviceBuilder(Set<PCComponent> pcComponentSet){
        this.pcComponentSet = pcComponentSet;
    }

    public Set<PCComponent> getPcComponentSet(){
        return pcComponentSet;
    }

    abstract public void buildDeviceSet(String fileName);
}
