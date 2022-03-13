package com.example.activity1.Infrastructure.Controller.services;

import com.example.activity1.Core.Domain.*;
import com.example.activity1.Core.Gateways.MaintenanceRepository;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceDTO;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceInput;
import com.example.activity1.shared.domain.Limit;
import com.example.activity1.shared.domain.PageQuery;
import com.example.activity1.shared.domain.Skip;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaintenanceServices {
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceServices(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public MaintenanceDTO createMaintenance(
            MaintenanceInput maintenanceInput
    ){
        String value = UUID.randomUUID().toString();
        MaintenanceService maintenance = new MaintenanceService(
                new MaintenanceId(value),
                new MaintenanceStartDateTime(maintenanceInput.getStartDateTime()),
                new MaintenanceEndDateTime(maintenanceInput.getEndDateTime()),
                new MaintenanceDescription(maintenanceInput.getDescription())
        );
        maintenanceRepository.store(maintenance);
        return  MaintenanceDTO.fromDomain(maintenance);
    }

    public List<MaintenanceDTO> queryMaintenance(Integer skip, Integer limit){
        PageQuery pageQuery = new PageQuery(
                new Skip(skip),
                new Limit(limit)
        );

        List<MaintenanceService> maintenanceServices = maintenanceRepository.queryMaintenance(pageQuery);

        List<MaintenanceDTO> result = new ArrayList<>();

        for (MaintenanceService maintenanceService: maintenanceServices) {
            MaintenanceDTO dto = MaintenanceDTO.fromDomain(maintenanceService);
            result.add(dto);
        }
        return  result;
    }

    public Optional<MaintenanceDTO> getMaintenance(String maintenanceId) {
        return maintenanceRepository.get(new MaintenanceId(maintenanceId))
                .map(maintenanceService -> {
                    return MaintenanceDTO.fromDomain(maintenanceService);
                });
    }

    public MaintenanceId deleteMaintenance(String maintenanceId) {
        return maintenanceRepository.deleteMaintenance(new MaintenanceId(maintenanceId));
    }
}
