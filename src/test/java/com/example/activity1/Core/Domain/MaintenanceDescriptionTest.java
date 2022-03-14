package com.example.activity1.Core.Domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceDescriptionTest {

    @Test
    @DisplayName("Null maintenanceDescription should throw an error")
    void null_description_test(){
        String unsafe = null;
        assertThrows(NullPointerException.class, () -> {
           new MaintenanceDescription(unsafe);
        });
    }

    @Test
    @DisplayName("Invalid maintenanceDescription should throw an error")
    void invalid_description_test(){
        String unsafe = "";
        assertThrows(IllegalArgumentException.class, () -> {
           new MaintenanceDescription(unsafe);
        });
    }

    @Test
    @DisplayName("Description longer than 512 characters shall throw an error")
    void longer_description_test(){
        String unsafe = "Unit Testing is a methodology of testing source code for its fitment of use in production.\n" +
                "We start out writing unit tests by creating various test cases to verify the behaviors of an individual unit of source code.\n" +
                "Then, the complete test suite executes to catch the regressions, either in the implementation phase or while building packages for various stages of deployments like staging and production.\n" +
                "Let's take a look at a simple scenario. To start with, let's create the Circle class and implement the calc";
        assertThrows(IllegalArgumentException.class, () ->{
            new MaintenanceDescription(unsafe);
        });
    }

    @Test
    @DisplayName("A valid description should throw an error")
    void valid_description_test(){
        String description = "This is proof of success";
        assertDoesNotThrow(() -> {
            new MaintenanceDescription(description);
        });
    }

}