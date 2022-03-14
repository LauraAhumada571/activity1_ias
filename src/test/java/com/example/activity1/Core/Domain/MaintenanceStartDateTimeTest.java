package com.example.activity1.Core.Domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceStartDateTimeTest {

    @Test
    @DisplayName("The start date time is not a future date")
    void future_datetime_start_datetime_test(){
        LocalDateTime unsafe = LocalDateTime.now();
        LocalDateTime future_datetime = unsafe.plusDays(1);
        assertThrows(IllegalArgumentException.class, () ->{
            new MaintenanceStartDateTime(future_datetime);
        });
    }

    @Test
    @DisplayName("Null maintenanceId should throw an error")
    void null_start_datetime_test(){
        LocalDateTime unsafe = null;
        assertThrows(NullPointerException.class, () -> {
           new MaintenanceStartDateTime(unsafe);
        });
    }

    @Test
    @DisplayName("A valid start datetime should not throw an error")
    void valid_start_datetime_test(){
        LocalDateTime unsafe = LocalDateTime.now();
        assertDoesNotThrow(() ->{
            new MaintenanceStartDateTime(unsafe);
        });
    }

}