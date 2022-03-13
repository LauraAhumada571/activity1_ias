package com.example.activity1.Infrastructure.Gateways.Repositories.models;

import com.example.activity1.Core.Domain.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MaintenanceDBO {
    private String idMaintenance;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;

    public MaintenanceDBO(String idMaintenance, LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.idMaintenance = idMaintenance;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public MaintenanceDBO() {
    }

    public MaintenanceService toDomain(){
        return new MaintenanceService(
                new MaintenanceId(idMaintenance),
                new MaintenanceStartDateTime(startDateTime),
                new MaintenanceEndDateTime(endDateTime),
                new MaintenanceDescription(description)
        );
    }

    public static MaintenanceDBO fromDomain(MaintenanceService maintenanceservice){
        return new MaintenanceDBO(
                maintenanceservice.getIdMaintenance().toString(),
                maintenanceservice.getStartDateTime().getValue(),
                maintenanceservice.getEndDateTime().getValue(),
                maintenanceservice.getDescription().toString()
        );
    }

    public static MaintenanceDBO fromResulSet(ResultSet resultSet) throws SQLException {
        MaintenanceDBO dbo = new MaintenanceDBO();
        dbo.setIdMaintenance(resultSet.getString("maintenance_id"));
        dbo.setStartDateTime(resultSet.getTimestamp("start_date_time").toLocalDateTime());
        dbo.setEndDateTime(resultSet.getTimestamp("end_date_time").toLocalDateTime());
        dbo.setDescription(resultSet.getString("description"));

        return dbo;
    }

    public String getIdMaintenance() {
        return idMaintenance;
    }

    public void setIdMaintenance(String idMaintenance) {
        this.idMaintenance = idMaintenance;
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