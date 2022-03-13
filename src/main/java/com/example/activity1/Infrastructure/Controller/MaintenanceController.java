package com.example.activity1.Infrastructure.Controller;

import com.example.activity1.Core.Domain.MaintenanceId;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceDTO;
import com.example.activity1.Infrastructure.Controller.models.MaintenanceInput;
import com.example.activity1.Infrastructure.Controller.services.MaintenanceServices;
import com.example.activity1.shared.errors.ApplicationError;
import com.sun.tools.javac.Main;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MaintenanceController {
    private final MaintenanceServices services;

    public MaintenanceController(MaintenanceServices services) {
        this.services = services;
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMaintenance(
            @PathVariable("id") String maintenanceId
    ) {
        Optional<MaintenanceDTO> maintenance = services.getMaintenance(maintenanceId);
        if (maintenance.isPresent()) {
            return ResponseEntity.ok(maintenance);
        } else {
            ApplicationError error = new ApplicationError(
                    "ResourceNotFound",
                    "Product with id not found",
                    Map.of("id", maintenanceId)
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public List<MaintenanceDTO> listMaintenance(
            @RequestParam(name = "skip", defaultValue = "0") Integer skip,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ) {

        return services.queryMaintenance(skip, limit);
    }

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public ResponseEntity<?> createMaintenance(
            @RequestBody MaintenanceInput maintenanceInput
    ) {
        try {
            MaintenanceDTO maintenanceDTO = services.createMaintenance(maintenanceInput);
            return ResponseEntity.ok(maintenanceDTO);
        } catch (IllegalArgumentException | NullPointerException e) {
            ApplicationError error = new ApplicationError(
                    "InputDataValidationError",
                    "Bad input data",
                    Map.of(
                            "error", e.getMessage()
                    )
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        } catch (Exception e) {
            ApplicationError error = new ApplicationError(
                    "SystemError",
                    e.getMessage(),
                    Map.of()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMaintenance(
            @PathVariable("id") String maintenanceId
    ){
        Optional<MaintenanceDTO> maintenance = services.getMaintenance(maintenanceId);

        if(maintenance.isPresent()){
            MaintenanceId id = services.deleteMaintenance(maintenanceId);
            return ResponseEntity.ok(id);
        } else {
            ApplicationError error = new ApplicationError(
                    "ResourceNotFound",
                    "Product with id not found",
                    Map.of("id", maintenanceId)
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

    @RequestMapping(value = "/services", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMaintenance(
            @PathVariable("id") String maintenanceId
    ){
        Optional<MaintenanceDTO> maintenance = services.getMaintenance(maintenanceId);

        if(maintenance.isPresent()){
            MaintenanceId id = services.deleteMaintenance(maintenanceId);
            return ResponseEntity.ok(id);
        } else {
            ApplicationError error = new ApplicationError(
                    "ResourceNotFound",
                    "Product with id not found",
                    Map.of("id", maintenanceId)
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(error);
        }
    }

}
