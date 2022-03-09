package com.example.activity1.Controller.models;

import com.example.activity1.Domain.*;

import java.time.LocalDateTime;

public class MaintenanceDTO {
    private String idMaintenance;
    private String startDateTime;
    private String endDateTime;
    private String description;

    public MaintenanceDTO(String idMaintenance, String startDateTime, String endDateTime, String description) {
        this.idMaintenance = idMaintenance;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public MaintenanceDTO() {
    }

    public static MaintenanceDTO fromDomain(MaintenanceService maintenanceservice){
        return new MaintenanceDTO(
                maintenanceservice.getIdMaintenance().toString(),
                maintenanceservice.getStartDateTime().toString(),
                maintenanceservice.getEndDateTime().toString(),
                maintenanceservice.getDescription().toString()
        );
    }

    public String getIdMaintenance() {
        return idMaintenance;
    }

    public void setIdMaintenance(String idMaintenance) {
        this.idMaintenance = idMaintenance;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
