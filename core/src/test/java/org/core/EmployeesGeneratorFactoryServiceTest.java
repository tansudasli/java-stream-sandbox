package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesGeneratorFactoryServiceTest {

    static int EMPLOYEE_COUNT = 19;
    static String EMPLOYEE_FIRST = "Ali";

    @Test
    @DisplayName("Test employee count == 19")
    void createEmployeesCount() {
        assertEquals(EMPLOYEE_COUNT, (long) EmployeesGeneratorFactoryService.create().size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void createEmployeeFirst() {
        assertEquals(EMPLOYEE_FIRST, EmployeesGeneratorFactoryService.create().stream()
                .findFirst()
                .orElse(null));
    }
}