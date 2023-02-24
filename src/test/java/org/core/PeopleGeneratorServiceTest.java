package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class PeopleGeneratorServiceTest {

    static int PEOPLE_COUNT = 4;
    static String PEOPLE_FIRST = "James";

    @Test
    @DisplayName("Test employee count == 4")
    void checkPeopleCount() {
        assertEquals(PEOPLE_COUNT, IPeopleGeneratorService.of.get().size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void checkFirstPeople() {
        assertEquals(PEOPLE_FIRST,
                IPeopleGeneratorService.of.get().stream()
                        .findFirst()
                        .get()
                        .firstName());
    }

    @Test
    void averageAge(){
        Supplier<IllegalArgumentException> missingAgeException = () -> new IllegalArgumentException("age missing!");

        var total = IPeopleGeneratorService.of.get()
                .stream()
                .map(Person::age)
                .reduce(Integer::sum)
                .orElseThrow(missingAgeException);


        var count = (long) IPeopleGeneratorService.of.get().size();

        System.out.printf("Age of Σ=%d | size=%d | average=%d\n", total, count, total/count);

    }
}