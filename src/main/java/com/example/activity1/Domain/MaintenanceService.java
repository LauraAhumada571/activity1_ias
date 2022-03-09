package com.example.activity1.Domain;

import org.apache.commons.lang3.Validate;

public class MaintenanceService {
    private final MaintenanceId idMaintenance;
    private final MaintenanceStartDateTime startDateTime;
    private final MaintenanceEndDateTime endDateTime;
    private final MaintenanceDescription description;

    public MaintenanceService(MaintenanceId idMaintenance, MaintenanceStartDateTime startDateTime, MaintenanceEndDateTime endDateTime, MaintenanceDescription description) {
        this.idMaintenance = idMaintenance;
        this.startDateTime = startDateTime;
        Validate.isTrue(endDateTime.getValue().isAfter(startDateTime.getValue()));
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public MaintenanceId getIdMaintenance() {
        return idMaintenance;
    }

    public MaintenanceStartDateTime getStartDateTime() {
        return startDateTime;
    }

    public MaintenanceEndDateTime getEndDateTime() { return endDateTime; }

    public MaintenanceDescription getDescription() {
        return description;
    }
}
