package com.example.activity1.Infrastructure.Controller;

import com.example.activity1.Core.Gateways.MaintenanceRepository;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceDTO;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceInput;
import com.example.activity1.Core.Domain.*;
import com.example.activity1.shared.domain.Limit;
import com.example.activity1.shared.domain.PageQuery;
import com.example.activity1.shared.domain.Skip;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MaintenanceController {
    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceController(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @RequestMapping (value = "/services/{id}", method = RequestMethod.GET)
    public MaintenanceDTO getMaintenance (
            @PathVariable("id") String maintenanceId
    ) {
        return new MaintenanceDTO(
                maintenanceId,
                "fake",
                "fake",
                "fake"
        );
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public List<MaintenanceDTO> listMaintenance(
            @RequestParam(name = "skip", defaultValue = "0") Integer skip,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ){
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

        return result;
    }

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public MaintenanceDTO createMaintenance(
            @RequestBody MaintenanceInput maintenanceInput
    ){
        MaintenanceService maintenanceservice = new MaintenanceService(
                new MaintenanceId(UUID.randomUUID().toString()),
                new MaintenanceStartDateTime(maintenanceInput.getStartDateTime()),
                new MaintenanceEndDateTime(maintenanceInput.getEndDateTime()),
                new MaintenanceDescription(maintenanceInput.getDescription())
        );
        return MaintenanceDTO.fromDomain(maintenanceservice);
    }

}
