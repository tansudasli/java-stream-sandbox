package org.core;

import org.junit.jupiter.api.Test;


import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PeopleSortTest {

    List<Person> people = IPeopleGeneratorService.of.get();

    Integer minAge = 19;
    Integer maxAge = 44;

    @Test
    void equalityOf() {

        //every data pieces must be same!!
        System.out.println(people.get(0));

        System.out.println(people.get(0).equals(new Person(1, "James", "Williams", "James@hotmail.com", GENDER.MALE, 44, Arrays.asList("1-857-545-5925", "1-894-887-1309"))));
        System.out.println(people.get(0).equals(new Person(1, null, "Williams", "James@hotmail.com", GENDER.MALE, 44, Arrays.asList("1-857-545-5925", "1-894-887-1309"))));
    }

    //choose Comparator interface over Comparable
    //no need anymore, to implement Comparable or Comparator
    //no need anymore, Collections.sort
    //use person.sort() instead
    //use lambda or Comparator. utilities @streams
    @Test
    void sortByAge() {
        //default stream.sorted() method waited People class must implement Comparable/Comparator interface.!
        //otherwise comparable cast error occurs

        /*
        sorting in 3 ways
        1- classical way,
           .sorted((person, t1) -> person.age() - t1.age())
        2- sorted(Comparator.comparingInt(Person::age)
        3- map(p -> p.age) then sorted()

        and 4th way is inplace-sorting
        people.sort(...) is also possible in 3 ways!!
         */
        peopleSortedByAgeStream3
                .get()
                .forEach(System.out::println);
    }
    public Supplier<Stream<Integer>> peopleSortedByAgeStream3 =
            () ->  people.stream()
                    .map(Person::age)
                    .sorted();

    public Supplier<Stream<Person>> peopleSortedByAgeStream2 =
            () ->  people.stream().sorted(Comparator.comparingInt(Person::age));

    public Supplier<Stream<Person>> peopleSortedByAgeStream1 =
            () ->  people.stream().sorted((person, t1) -> person.age() - t1.age());

    //inplace sorting
    public Runnable peopleSortedByAge = () ->  people.sort(Comparator.comparingInt(Person::age));

    @Test
    void sortedByIDReverse() {
        //just reverse target and first object
        people.stream()
                .sorted((person, t1) -> t1.id() - person.id())
                .forEach(System.out::println);
    }

}
