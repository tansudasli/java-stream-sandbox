package org.core;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeopleSetTest {

    private static final Integer CAPACITY = IPeopleGeneratorService.of.get().size();
    private static final Integer SIZE = CAPACITY - 1;

    Set<Person> data = IPeopleGeneratorService.of.get()
                                      .stream()
                                      .collect(Collectors.toSet());

    @Test
    void testPeopleCount() {

        assertEquals(SIZE, data.size());
    }
}
