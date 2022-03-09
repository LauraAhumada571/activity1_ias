package com.example.activity1.Domain;

import org.apache.commons.lang3.Validate;

public class MaintenanceDescription {
    private final String value;

    public MaintenanceDescription(String value) {
        Validate.notNull(value, "Maintenance description can not be null");
        Validate.notBlank(value, "Maintenance description can not be blank");
        Validate.isTrue(value.trim().length() <= 512, "Maintenance description can not be longer than 512");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
