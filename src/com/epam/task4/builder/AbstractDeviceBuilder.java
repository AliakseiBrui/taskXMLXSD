package com.epam.task4.builder;

import com.epam.task4.entity.PcComponent;
import com.epam.task4.entity.Phone;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDeviceBuilder {
    Set<PcComponent> pcComponentSet;
    Set<Phone> phoneSet;

    AbstractDeviceBuilder(){
        pcComponentSet = new HashSet<>();
        phoneSet = new HashSet<>();
    }

    public AbstractDeviceBuilder(Set<PcComponent> pcComponentSet, Set<Phone> phoneSet) {
        this.pcComponentSet = pcComponentSet;
        this.phoneSet = phoneSet;
    }

    public Set<PcComponent> getPcComponentSet(){
        return pcComponentSet;
    }

    public Set<Phone> getPhoneSet() {
        return phoneSet;
    }

    public abstract void buildDeviceSet(String fileName);
}
