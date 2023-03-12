package org.core;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapTest {

    /*
      - Map<k, v>.entrySet().stream()

     */
    Map<Integer, Person> data = IPeopleGeneratorService.of.get()
                                           .stream().distinct()
                                           .collect(Collectors.toMap(Person::id, Function.identity()));


    @Test
    void printDataMap() {

//        System.out.println(dataMap);

        //getting a stream gives more methods & flexibility
//        data.forEach((key, person) ->
//                System.out.println(String.join("=", key.toString(), person.firstName())));

        data.entrySet()
                .stream()
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey().toString()
                                   .concat(":" + entry.getValue().email())));
    }

    // Map<age, List<Person>>
    Map<Integer, List<Person>> personGroupedByAge = IPeopleGeneratorService.of.get()
            .stream()
            .limit(5)
            .collect(Collectors.groupingBy(Person::age));

    @Test
    void printPersonGroupedByAge() {
        personGroupedByAge.forEach((k, v) -> System.out.println(k + ":" + v.size()));
    }
}
