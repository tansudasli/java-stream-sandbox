package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class PeopleGeneratorServiceTest {

    static int PEOPLE_COUNT = 4;
    static String PEOPLE_FIRST = "James";
    List<Person> people = IPeopleGeneratorService.of.get();

    @Test
    @DisplayName("Test employee count == 4")
    void checkPeopleCount() {
        assertEquals(PEOPLE_COUNT, people.size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void checkFirstPeople() {
        assertEquals(PEOPLE_FIRST, people.stream()
                                            .findFirst()
                                            .get()
                                            .firstName());
    }

    @Test
    void printPhones() {
//flatmap Stream<Stream<T>>

        people.stream()
                .flatMap((person -> person.phones().stream()))
                .forEach(System.out::println);

        people.stream()
                .forEach(System.out::println);
    }


}