package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeopleGeneratorServiceTest {

    static int PEOPLE_COUNT = 4;
    static String PEOPLE_FIRST = "James";

    @Test
    @DisplayName("Test employee count == 122")
    void checkPeopleCount() {
        assertEquals(PEOPLE_COUNT, IPeopleGeneratorService.of.get().size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void checkFirstPeople() {
        assertEquals(PEOPLE_FIRST, IPeopleGeneratorService.of.get().stream()
                .findFirst()
                .get()
                .firstName());
    }
}