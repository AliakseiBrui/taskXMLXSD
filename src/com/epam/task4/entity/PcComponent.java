package com.epam.task4.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class PcComponent extends Device {
    private boolean isCritical;
    private ComponentType componentType;

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public PcComponent() {
        this.componentType = new ComponentType();
    }

    public PcComponent(String deviceId, String deviceName, String originCountry, BigDecimal devicePrice, boolean isCritical, ComponentType componentType) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.originCountry = originCountry;
        this.devicePrice = devicePrice;
        this.isCritical = isCritical;
        this.componentType = componentType;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    @Override
    public String toString() {
        return "PcComponent{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", devicePrice=" + devicePrice +
                ", isCritical=" + isCritical +
                ", componentType=" + componentType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PcComponent pcComponent = (PcComponent) o;
        return isCritical == pcComponent.isCritical &&
                Objects.equals(deviceId, pcComponent.deviceId) &&
                Objects.equals(deviceName, pcComponent.deviceName) &&
                Objects.equals(originCountry, pcComponent.originCountry) &&
                Objects.equals(devicePrice, pcComponent.devicePrice) &&
                Objects.equals(componentType, pcComponent.componentType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deviceId, deviceName, originCountry, devicePrice, isCritical, componentType);
    }



}
