package com.example.activity1.Controller.models;

import java.time.LocalDateTime;

public class MaintenanceInput {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public MaintenanceInput(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public MaintenanceInput() {
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

