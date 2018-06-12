package com.epam.task4.entity;

public enum DeviceEnum {
        DEVICES("devices"),
        PC_COMPONENT("pc-component"),
        PHONE("phone"),
        COMPONENT_TYPE("component-type"),
        ID("id"),
        NAME("name"),
        ORIGIN_COUNTRY("origin-country"),
        PRICE("price"),
        PORT("port"),
        PERIPHERAL("peripheral"),
        CRITICAL("critical"),
        COMPONENT_GROUP("component-group"),
        HAS_COOLER("has-cooler"),
        BUILD_DATE("build-date"),
        RAM("ram"),
        ENERGY_CONSUMPTION("energy-consumption"),
        NONE;

        private String tag;

        DeviceEnum(){}

        DeviceEnum(String tag) {
            this.tag = tag;
        }

        public String getTag() {
            return tag;

        }
        public static DeviceEnum checkTag(String value){

            for(DeviceEnum deviceEnum : DeviceEnum.values()){

                if(deviceEnum.tag.equals(value)){
                    return deviceEnum;
                }
            }
            return NONE;
        }
}
