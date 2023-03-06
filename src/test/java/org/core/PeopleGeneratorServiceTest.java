package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PeopleGeneratorServiceTest {


    @Test
    @DisplayName("Test employee count == 4")
    void checkPeopleCount() {
        assertEquals(5, IPeople.people.size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void checkFirstPeople() {
        assertEquals("James", IPeople.people.stream()
                                            .findFirst()
                                            .get()
                                            .firstName());
    }

    @Test
    void print() {
        IPeople.people.stream()
                .forEach(System.out::println);
    }

    /*
    if List<...>, access all values using flatMap(x -> x.stream)

    flatmap Stream<Stream<T>>
     */
    @Test
    void printPhones() {

        IPeople.people.stream()
                .flatMap(person -> person.phones().stream())
                .forEach(System.out::println);
    }

    @Test
    void concatNameAndLastName() {
        System.out.println(concatNameAndLastNameAsOneWord.get());
    }

    /*
    alixyzveliklmabidinvyz
     */
    private final Supplier<String> concatNameAndLastNameAsOneWord =
            () -> IPeople.people.stream()
                    .map(person -> person.firstName().toLowerCase().concat(person.lastname().toLowerCase()))
                    .collect(Collectors.joining(""));


}