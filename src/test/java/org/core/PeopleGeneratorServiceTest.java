package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PeopleGeneratorServiceTest {

    static int PEOPLE_COUNT = 5;
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
    void print() {
        people.stream()
                .forEach(System.out::println);
    }

    /*
    if List<...>, access all values using flatMap(x -> x.stream)

    flatmap Stream<Stream<T>>
     */
    @Test
    void printPhones() {

        people.stream()
                .flatMap(person -> person.phones().stream())
                .forEach(System.out::println);
    }

    @Test
    void concatNameAndLastName() {
        System.out.println(concatNameAndLastName.get());
    }

    /*
    alixyzveliklmabidinvyz
     */
    private final Supplier<String> concatNameAndLastName =
            () -> people.stream()
                    .map(person -> person.firstName().toLowerCase() + person.lastname().toLowerCase())
                    .collect(Collectors.joining(""));


}