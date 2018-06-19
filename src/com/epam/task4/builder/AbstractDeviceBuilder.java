package com.epam.task4.builder;

import com.epam.task4.entity.PCComponent;
import com.epam.task4.entity.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeviceBuilder {
    Set<PCComponent> pcComponentSet;
    Set<Phone> phoneSet;

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
