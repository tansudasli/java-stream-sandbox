package org.core.collections;

import org.core.IPeopleGeneratorService;
import org.core.Person;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapTest {
    /* general combinations


    Concurrent | Array   | List
                 Linked  | Map
                 Hash    | Set
                 Tree    | Queue
                         | Table
     */


    /*default: initial bucket = 16, load factor 0.75
               if (size = lf * initial) -> resize *= 2

      - Map<k, v>.entrySet().stream() ..... one key = one value
      - HashMap,                      ..... one key = N values !

      buckets
        0
        1  |        Node              |
        2  | key|value|hash(key)|next | ....LinkedList<value> .....
        3
        .
        .
        15
        if key==null, bucket will be 0!

        bucket index => hash(key) & (length-1)

      - ConcurrentHashMap, no-null, trade-safe ..... one key = N values !
      - TreeMap

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
}
