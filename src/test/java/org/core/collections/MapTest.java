package org.core.collections;

import org.core.IPeopleGeneratorService;
import org.core.Person;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapTest {
    /* general combinations


    Concurrent | Linked     | Array   | List
                 Navigable    Hash    | Map
                              Tree    | Set
                                      | Queue
                                      | Table
     */


    /*default: initial bucket = 16, load factor 0.75
               if (size = lf * initial) -> resize *= 2

               use .entrySet().stream() for streaming

     Map means, one key = one value, But if you put a List<V>, you can hold many values for one key!

      - Map<k, v>, unsorted, no-null-key, no-duplicate-key           ..... one key = one value
      - HashMap, unsorted, null-key, no-duplicate-key                .....
      - ConcurrentHashMap, no-null-key, trade-safe                   .....
      - TreeMap,sorted (natural order)
        detail: always compareTo last node, then if greater puts right, otherwise left side.

     */

    /* HashMap in detail

      buckets
        0  |   null-key is ok, reserved for null    |
        1
        2
        3  |        Node              |   NodeN
        4  | key|value|hash(key)|next | ....LinkedList<value> .....
        5
        .
        .
        15
        if key==null, bucket will be 0!

        bucket index => hash(key) & (size-1)
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
            .collect(Collectors.groupingBy(Person::age));

    @Test
    void printPersonGroupedByAge() {
        //get entrySet to get a stream and flexibility!

        System.out.println("Size of map = ".concat(String.valueOf(personGroupedByAge.size())));
        personGroupedByAge.forEach((k, v) -> System.out.println(k + ":" + v.size()));
    }


    //ConcurrentHashMap<age, List<Person>>
    ConcurrentHashMap<Integer, List<Person>> personGroupedByAgeConcurrent = new ConcurrentHashMap<>(personGroupedByAge);
    HashMap<Integer, List<Person>> personGroupedByAgeHashed = new HashMap<>(personGroupedByAge);

    @Test
    void personGroupedByAgeConcurrent() {

        var entry = personGroupedByAgeConcurrent.entrySet()
                .stream()
                .findFirst()
                .orElseThrow();

        System.out.println("Size of first key=(age of 15) | ".concat(String.valueOf(entry.getValue().size())));

        System.out.println(entry.getValue()
                                .stream()
                                .map(Person::firstName)
                                .collect(Collectors.joining(", ")));


    }

    /*
    HashMap<Integer, String> works different from HashMap<Integer, List<Person>>

    1st adds k=v
    2nd adds k=list of v ... so has many pointers, in fact

    we may have delusion that more keys added :)
     */
    @Test
    void personGroupedByAgeHashed() {

        var entry = personGroupedByAgeHashed.entrySet()
                .stream()
                .findFirst()
                .orElseThrow();

        System.out.println("Size of first key=(age of 15) | ".concat(String.valueOf(entry.getValue().size())));

        System.out.println(entry.getValue()
                .stream()
                .map(Person::firstName)
                .collect(Collectors.joining(", ")));


    }
}
