package org.core;

import org.junit.jupiter.api.Test;


import java.util.*;

public class PeopleSortTest {


    @Test
    void equalityOf() {

        //every data pieces must be same!!
        System.out.println(IPeople.people.get(0));

        System.out.println(IPeople.people.get(0).equals(new Person(1, "James", "Williams", "James@hotmail.com", GENDER.MALE, 44, Arrays.asList("1-857-545-5925", "1-894-887-1309"))));
        System.out.println(IPeople.people.get(0).equals(new Person(1, null, "Williams", "James@hotmail.com", GENDER.MALE, 44, Arrays.asList("1-857-545-5925", "1-894-887-1309"))));
    }

    @Test
    void sortByAge() {
        IPeopleSorted.peopleSortedByAgeStream
                .get()
                .forEach(System.out::println);
    }

    //comparator chaining
    @Test
    void reverseSortByAgeThenFirstName() {
        IPeopleSorted.peopleReverseSortedByAgeThenFirstNameStream.get()
                .forEach(System.out::println);
    }

    @Test
    void peopleSortedByAgeThenGenderStream() {
        IPeopleSorted.peopleSortedByAgeThenGenderStream.get()
                .forEach(System.out::println);
    }
    @Test
    void sortedByIDReverse() {
        //just reverse target and first object
        IPeople.people.stream()
                .sorted((person, t1) -> t1.id() - person.id())
                .forEach(System.out::println);
    }

}
