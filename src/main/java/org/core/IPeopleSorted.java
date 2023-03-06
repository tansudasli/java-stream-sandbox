package org.core;

import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface IPeopleSorted {
    /* Some general concepts

      - choose Comparator interface over Comparable
      - no need anymore, to implement Comparable or Comparator
      - no need anymore, Collections.sort
      - use person.sort() instead (inplace, if immutable it ain't work
      - use lambda or Comparator. utilities @streams, map whenever you need!
     */

    /* sorting in 3 ways

        1- classical way,
           .sorted((person, t1) -> person.age() - t1.age())
        2- sorted(Comparator.comparingInt(Person::age)
        3- map(p -> p.age) then sorted()

        and 4th way is inplace-sorting
        people.sort(...) is also possible in 3 ways!! And, if it ain't work for immutable things!
     */

    Supplier<Stream<Integer>> peopleSortedByAgeStream =
            () ->  IPeople.people.stream()
                    .map(Person::age)
                    .sorted();

    Supplier<Stream<Person>> peopleReverseSortedByAgeThenFirstNameStream =
            () ->  IPeople.people
                    .stream()
                    .sorted(Comparator.comparingInt(Person::age)
                            .reversed()    //where we used is important !
                            .thenComparing(Person::firstName));

    Supplier<Stream<Person>> peopleSortedByAgeThenGenderStream =
            () ->  IPeople.people
                    .stream()
                    .sorted((person, t1) -> person.age() - t1.age())
                    .sorted(Comparator.comparing(Person::lastname));  //2 sorted means, last one works 1st!

    /*
     inplace sorting, and it is void!
     so Runnable interface is appropriate, as functional interface!
     */
    Runnable peopleSortedByAge = () ->  {
        System.out.println("peopleSortedByAge::: ".concat(Thread.currentThread().getName()));

        IPeopleGeneratorService.dataMutable.sort(Comparator.comparingInt(Person::age));
    };

}
