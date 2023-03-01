package org.core;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

public class PeopleMapTest {

    Map<Integer, Person> dataMap = IPeopleGeneratorService.of.get()
                                           .stream()
                                           .collect(Collectors.toMap(Person::id, person -> person));

    @Test
    void printDataMap() {

//        System.out.println(dataMap);

        dataMap.forEach((key, person) -> System.out.println(String.join("=",
                                                                          key.toString(),
                                                                          person.firstName())));
    }

}
