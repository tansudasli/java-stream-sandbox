package org.example;

import org.core.IEmployeesGeneratorFactoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Can fp module access to core module ?")
    void checkEmployeesGeneratorFactoryServiceAccess() {
        assertEquals(5, IEmployeesGeneratorFactoryService.create().size());
    }

}