package org.core.collections;

import org.core.IPeopleGeneratorService;
import org.core.Person;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetTest {

    private static final Integer CAPACITY = IPeopleGeneratorService.of.get().size();
    private static final long SIZE =  IPeopleGeneratorService.of.get()
                                            .stream().distinct()
                                            .count();

    //creates a unique list of data!
    Set<Person> data = IPeopleGeneratorService.of.get()
                                      .stream()
                                      .collect(Collectors.toSet());

    @Test
    void testPeopleCount() {
        System.out.println("people count: ".concat(String.valueOf(data.size())));

        assertEquals(SIZE, data.size());
    }
}
