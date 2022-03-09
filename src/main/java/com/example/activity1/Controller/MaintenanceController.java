package com.example.activity1.Controller;

import com.example.activity1.Controller.models.MaintenanceDTO;
import com.example.activity1.Controller.models.MaintenanceInput;
import com.example.activity1.Domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MaintenanceController {
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
        return List.of();
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
