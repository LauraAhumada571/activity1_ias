package com.example.activity1.Core.Domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceIdTest {

    @Test
    @DisplayName("Null maintenanceId should throw an error")
    void null_maintenance_id_test(){
        String unsafeId = null;
        assertThrows(NullPointerException.class, () ->{
            new MaintenanceId(unsafeId);
        });
    }

    @Test
    @DisplayName("Invalid maintenanceId should throw an error")
    void invalid_maintenance_id_test(){
        String unsafeId = "";
        assertThrows(IllegalArgumentException.class, () ->{
            new MaintenanceId(unsafeId);
        });
    }

    @Test
    @DisplayName("A valid id should not throw an error")
    void valid_id_test(){
        String validId = UUID.randomUUID().toString();
        assertDoesNotThrow(() ->{
            new MaintenanceId(validId);
        });
    }
}