package com.epam.task4.entity;

public enum DeviceEnum {
        DEVICES("devices"),
        DEVICE("device"),
        DEVICE_TYPE("device-type"),
        ID("id"),
        NAME("name"),
        ORIGIN_COUNTRY("origin-country"),
        PRICE("price"),
        PORT("port"),
        PERIPHERAL("peripheral"),
        CRITICAL("critical"),
        DEVICE_GROUP("device-group"),
        HAS_COOLER("has-cooler"),
        ENERGY_CONSUMPTION("energy-consumption"),
        NONE;

        private String value;

        DeviceEnum(){}

        DeviceEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;

        }
        public static DeviceEnum checkValue(String value){

            for(DeviceEnum deviceEnum : DeviceEnum.values()){

                if(deviceEnum.value.equals(value)){
                    return deviceEnum;
                }
            }
            return NONE;
        }
}
