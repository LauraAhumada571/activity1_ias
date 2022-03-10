package com.example.activity1.Core.Domain;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;

public class MaintenanceStartDateTime {
    private final LocalDateTime value;

    public MaintenanceStartDateTime(LocalDateTime value) {
        Validate.notNull(value, "Start date time of maintenance can not be null");
        Validate.isTrue(value.isBefore(LocalDateTime.now()));
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MaintenanceStartDateTime{" +
                "value=" + value +
                '}';
    }
}
