package com.example.activity1.Domain;

import org.apache.commons.lang3.Validate;

import java.time.LocalDateTime;

public class MaintenanceEndDateTime {
    private final LocalDateTime value;

    public MaintenanceEndDateTime(LocalDateTime value) {
        Validate.notNull(value, "End date time of maintenance can not be null");
        Validate.isTrue(value.isBefore(LocalDateTime.now()));
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }
}
