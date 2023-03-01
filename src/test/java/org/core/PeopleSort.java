package org.core;

import org.junit.jupiter.api.Test;


import java.util.*;

public class PeopleSort {

    List<Person> people = IPeopleGeneratorService.of.get();

    Integer minAge = 19;
    Integer maxAge = 44;

    @Test
    void equalityOf() {

        //every data pieces must be same!!
        System.out.println(people.get(0));

        System.out.println(people.get(0).equals(new Person(1, "James", "Williams", "James@hotmail.com", "Male", 44, Arrays.asList("1-857-545-5925", "1-894-887-1309"))));
        System.out.println(people.get(0).equals(new Person(1, null, "Williams", "James@hotmail.com", "Male", 44, Arrays.asList("1-857-545-5925", "1-894-887-1309"))));
    }

    @Test
    void sortByAge() {
        //default stream.sorted() method waited People class must implement Comparable/Comparator interface.!
        //otherwise comparable cast error occurs

        people.stream()
                .sorted(Comparator.comparingInt(Person::age))
//                .sorted(((person, t1) -> person.age() - t1.age()))
                .forEach(System.out::println);

//        assertEquals(people.stream()
//                .sorted(((person, t1) -> person.age() - t1.age()))
//                .findFirst()
//                .orElseThrow()
//                .age(), minAge);

    }

    @Test
    void sortReverseByID() {
        //just reverse target and first object
        people.stream()
                .sorted((person, t1) -> t1.id() - person.id())
                .forEach(System.out::println);
    }

}
