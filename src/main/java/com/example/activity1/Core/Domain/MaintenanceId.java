package com.example.activity1.Core.Domain;

import org.apache.commons.lang3.Validate;

public class MaintenanceId {
    private final String value;

    public MaintenanceId(String value) {
        Validate.notNull(value, "Maintenance id can not be null");
        Validate.notBlank(value, "Maintenance id can not be blank");
        Validate.isTrue(value.trim().length() == 36, "Maintenance id must be 36 characteres");
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

