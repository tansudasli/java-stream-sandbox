package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesGeneratorFactoryServiceTest {

    static int EMPLOYEE_COUNT = 5;
    static String EMPLOYEE_FIRST = "Ali";

    @Test
    @DisplayName("Test employee count == 5")
    void checkEmployeesCount() {
        assertEquals(EMPLOYEE_COUNT, IEmployeesGeneratorFactoryService.create().size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void checkFirstEmployee() {
        assertEquals(EMPLOYEE_FIRST, IEmployeesGeneratorFactoryService.create().stream()
                .findFirst()
                .orElse(null));
    }
}