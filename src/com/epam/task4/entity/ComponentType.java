package com.epam.task4.entity;

import java.util.Objects;

public class ComponentType {
    public enum ComponentGroup {
        IO,MULTIMEDIA
    }

    public enum Port{
        COM,USB,LPT,PCI,SATA
    }

    private boolean isPeripheral;
    private int energyConsumption;
    private boolean hasCooler;
    private ComponentGroup componentGroup;
    private Port port;

    public ComponentType() {
    }

    public ComponentType(boolean isPeripheral, int energyConsumption, boolean hasCooler, ComponentGroup componentGroup, Port port) {
        this.isPeripheral = isPeripheral;
        this.energyConsumption = energyConsumption;
        this.hasCooler = hasCooler;
        this.componentGroup = componentGroup;
        this.port = port;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public void setPeripheral(boolean peripheral) {
        isPeripheral = peripheral;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public boolean isHasCooler() {
        return hasCooler;
    }

    public void setHasCooler(boolean hasCooler) {
        this.hasCooler = hasCooler;
    }

    public ComponentGroup getComponentGroup() {
        return componentGroup;
    }

    public void setComponentGroup(ComponentGroup componentGroup) {
        this.componentGroup = componentGroup;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ComponentType{" +
                "isPeripheral=" + isPeripheral +
                ", energyConsumption=" + energyConsumption +
                ", hasCooler=" + hasCooler +
                ", componentGroup=" + componentGroup +
                ", port=" + port +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentType that = (ComponentType) o;
        return isPeripheral == that.isPeripheral &&
                energyConsumption == that.energyConsumption &&
                hasCooler == that.hasCooler &&
                componentGroup == that.componentGroup &&
                port == that.port;
    }

    @Override
    public int hashCode() {

        return Objects.hash(isPeripheral, energyConsumption, hasCooler, componentGroup, port);
    }
}
