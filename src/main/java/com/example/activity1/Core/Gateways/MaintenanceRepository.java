package com.example.activity1.Core.Gateways;

import com.example.activity1.Core.Domain.MaintenanceId;
import com.example.activity1.Core.Domain.MaintenanceService;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceInput;
import com.example.activity1.shared.domain.PageQuery;

import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository {
    List<MaintenanceService> queryMaintenance(PageQuery pageQuery);

    Optional<MaintenanceService> get(MaintenanceId maintenanceId);

    void store (MaintenanceService maintenanceService);

    MaintenanceId deleteMaintenance(MaintenanceId maintenanceId);

    MaintenanceInput updateMaintenance(MaintenanceId maintenanceId, MaintenanceInput maintenanceInput);
}
