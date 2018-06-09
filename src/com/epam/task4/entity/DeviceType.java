package com.epam.task4.entity;

import java.util.Objects;

public class DeviceType{
    public enum DeviceGroup{
        IO,MULTIMEDIA
    }

    public enum Port{
        COM,USB,LPT,PCI,SATA
    }

    private boolean isPeripheral;
    private int energyConsumption;
    private boolean hasCooler;
    private DeviceGroup deviceGroup;
    private Port port;

    public DeviceType() {
    }

    public DeviceType(boolean isPeripheral, int energyConsumption, boolean hasCooler, DeviceGroup deviceGroup, Port port) {
        this.isPeripheral = isPeripheral;
        this.energyConsumption = energyConsumption;
        this.hasCooler = hasCooler;
        this.deviceGroup = deviceGroup;
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

    public DeviceGroup getDeviceGroup() {
        return deviceGroup;
    }

    public void setDeviceGroup(DeviceGroup deviceGroup) {
        this.deviceGroup = deviceGroup;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "DeviceType{" +
                "isPeripheral=" + isPeripheral +
                ", energyConsumption=" + energyConsumption +
                ", hasCooler=" + hasCooler +
                ", deviceGroup=" + deviceGroup +
                ", port=" + port +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceType that = (DeviceType) o;
        return isPeripheral == that.isPeripheral &&
                energyConsumption == that.energyConsumption &&
                hasCooler == that.hasCooler &&
                deviceGroup == that.deviceGroup &&
                port == that.port;
    }

    @Override
    public int hashCode() {

        return Objects.hash(isPeripheral, energyConsumption, hasCooler, deviceGroup, port);
    }
}
