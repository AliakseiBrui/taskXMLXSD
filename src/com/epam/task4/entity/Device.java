package com.epam.task4.entity;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Device {
    String deviceId;
    String deviceName;
    String originCountry;
    BigDecimal devicePrice;

    public Device(String deviceId, String deviceName, String originCountry, BigDecimal devicePrice) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.originCountry = originCountry;
        this.devicePrice = devicePrice;
    }

    public Device() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public BigDecimal getDevicePrice() {
        return devicePrice;
    }

    public void setDevicePrice(BigDecimal devicePrice) {
        this.devicePrice = devicePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(deviceId, device.deviceId) &&
                Objects.equals(deviceName, device.deviceName) &&
                Objects.equals(originCountry, device.originCountry) &&
                Objects.equals(devicePrice, device.devicePrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deviceId, deviceName, originCountry, devicePrice);
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", devicePrice=" + devicePrice +
                '}';
    }
}
