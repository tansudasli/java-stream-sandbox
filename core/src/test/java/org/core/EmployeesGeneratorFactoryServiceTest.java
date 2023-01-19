package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesGeneratorFactoryServiceTest {

    @Test
    @DisplayName("Test employee count == 19")
    void createEmployeesCount() {
        assertEquals(19, (long) EmployeesGeneratorFactoryService.create().size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void createEmployeeFirst() {
        assertEquals("Ali", EmployeesGeneratorFactoryService.create().stream().findFirst().get());
    }
}