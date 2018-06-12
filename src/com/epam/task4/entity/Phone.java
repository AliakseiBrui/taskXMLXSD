package com.epam.task4.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Phone extends Device{

    private Integer ram;
    private Date buildDate;

    public Phone(String deviceId, String deviceName, String originCountry, BigDecimal devicePrice, Integer ram, Date buildDate) {
        super(deviceId, deviceName, originCountry, devicePrice);
        this.ram = ram;
        this.buildDate = buildDate;
    }

    public Phone(Integer ram, Date buildDate) {
        this.ram = ram;
        this.buildDate = buildDate;
    }

    public Phone() {
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Phone phone = (Phone) o;
        return Objects.equals(ram, phone.ram) &&
                Objects.equals(buildDate, phone.buildDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), ram, buildDate);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "ram=" + ram +
                ", buildDate=" + buildDate +
                ", deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", devicePrice=" + devicePrice +
                '}';
    }
}
