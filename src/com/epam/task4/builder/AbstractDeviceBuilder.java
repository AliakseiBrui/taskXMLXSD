package com.epam.task4.builder;

import com.epam.task4.entity.PCComponent;
import com.epam.task4.entity.Phone;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeviceBuilder {
    protected Set<PCComponent> pcComponentSet;
    protected Set<Phone> phoneSet;

    AbstractDeviceBuilder(){
        pcComponentSet = new HashSet<>();
        phoneSet = new HashSet<>();
    }

    public AbstractDeviceBuilder(Set<PCComponent> pcComponentSet, Set<Phone> phoneSet) {
        this.pcComponentSet = pcComponentSet;
        this.phoneSet = phoneSet;
    }

    public Set<PCComponent> getPcComponentSet(){
        return pcComponentSet;
    }

    public Set<Phone> getPhoneSet() {
        return phoneSet;
    }

    public abstract void buildDeviceSet(String fileName);
}
