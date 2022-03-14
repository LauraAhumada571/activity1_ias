package com.example.activity1.Core.Domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceServiceTest {

    String validId = UUID.randomUUID().toString();
    LocalDateTime startDateTime = LocalDateTime.now().minusHours(3);
    String description = "this is a test";

    @Test
    @DisplayName("Invalid endDateTime is earlier than the startDateTime should throw an error")
    void invalid_after_end_datetime_test(){
        LocalDateTime endDateTime = LocalDateTime.now().minusHours(4);
        assertThrows(IllegalArgumentException.class, () -> {
           new MaintenanceService(
                   new MaintenanceId(validId),
                   new MaintenanceStartDateTime(startDateTime),
                   new MaintenanceEndDateTime(endDateTime),
                   new MaintenanceDescription(description)
           );
        });
    }

    @Test
    @DisplayName("A valid endDateTime should not throw an error")
    void valid_end_datetime_test(){
        LocalDateTime endDateTime = LocalDateTime.now().minusHours(2);
        assertDoesNotThrow(() -> {
            new MaintenanceService(
                    new MaintenanceId(validId),
                    new MaintenanceStartDateTime(startDateTime),
                    new MaintenanceEndDateTime(endDateTime),
                    new MaintenanceDescription(description)
            );
        });
    }

}