package org.core;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

public class MapTest {

    Map<Integer, Person> data = IPeopleGeneratorService.of.get()
                                           .stream().distinct()
                                           .collect(Collectors.toMap(Person::id, person -> person));

    @Test
    void printDataMap() {

//        System.out.println(dataMap);

        data.forEach((key, person) ->
                System.out.println(String.join("=", key.toString(), person.firstName())));
    }

}
