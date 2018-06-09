package com.epam.task4.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Device {
    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    private String deviceId;
    private String deviceName;
    private String originCountry;
    private BigDecimal devicePrice;
    private boolean isCritical;
    private DeviceType deviceType;

    public Device() {
        this.deviceType = new DeviceType();
    }

    public Device(String deviceId, String deviceName, String originCountry, BigDecimal devicePrice, boolean isCritical, DeviceType deviceType) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.originCountry = originCountry;
        this.devicePrice = devicePrice;
        this.isCritical = isCritical;
        this.deviceType = deviceType;
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

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", devicePrice=" + devicePrice +
                ", isCritical=" + isCritical +
                ", deviceType=" + deviceType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return isCritical == device.isCritical &&
                Objects.equals(deviceId, device.deviceId) &&
                Objects.equals(deviceName, device.deviceName) &&
                Objects.equals(originCountry, device.originCountry) &&
                Objects.equals(devicePrice, device.devicePrice) &&
                Objects.equals(deviceType, device.deviceType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deviceId, deviceName, originCountry, devicePrice, isCritical, deviceType);
    }



}
