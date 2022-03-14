package com.example.activity1.Core.Domain;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceEndDateTimeTest {

    @Test
    @DisplayName("Null endDateTime should throw an error")
    void null_end_datetime_test(){
        String unsafe = null;
        assertThrows(NullPointerException.class, () -> {
            new MaintenanceDescription(unsafe);
        });
    }

    @Test
    @DisplayName("EndDateTime is not a future date")
    void future_date_end_datetime_test(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime unsafe = now.minusMinutes(5);
        assertDoesNotThrow(() -> {
            new MaintenanceEndDateTime(unsafe);
        });
    }

    @Test
    @DisplayName("A valid endDateTime should throw an error")
    void valid_end_datetime_test(){
        LocalDateTime now_date = LocalDateTime.now();
        assertDoesNotThrow(() -> {
            new MaintenanceEndDateTime(now_date);
        });
    }

}