package org.core;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class FilteringTest {

    @Test
    void queryByAgeGreaterThan20() {
        Predicate<Person> isAgeGreaterThan20 = person -> person.age() > 20;
        Predicate<Person> isFemale = person -> person.gender().equals("Female");

        IPeopleGeneratorService.of.get()
                .stream()
                .filter(isAgeGreaterThan20
                        .and(isFemale))         //concatenation
                .forEach(System.out::println);
    }
}
