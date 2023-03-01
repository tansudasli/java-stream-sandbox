package org.core;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

public class PeopleStatisticTest {

    private static final Integer CAPACITY = IPeopleGeneratorService.of.get().size();
    private static final long SIZE = IPeopleGeneratorService.of.get()
                                                .stream().distinct()
                                                .count();

    List<Person> people = IPeopleGeneratorService.of.get();

    @Test
    void sumOfAge(){

        //in parallelStream, results can be combined w/ another BiOperator !
        System.out.println(
                people.parallelStream()
                        .map(Person::age)
                        .reduce(0,                //identity: u = 0
                                 Integer::sum,        // (u, t) -> u + t
                                 Integer::sum));      // (u, u) -> u + u and returns u

    }

    @Test
    void meanOfAge(){
        //Todo: make better exception handling
        Supplier<IllegalArgumentException> missingAgeException = () -> new IllegalArgumentException("age missing!");

        var total = people.stream()
                .distinct()                          //uses hashCode() and equals() which by default counts all fields!
                .map(Person::age)
                .reduce(Integer::sum)
                .orElseThrow(missingAgeException);

        System.out.printf("Size=%d | Capacity=%d\n", SIZE, CAPACITY);
        System.out.printf("Age of Σ=%d | size=%d | average=%f\n", total, SIZE, (double)(total/CAPACITY));

    }
    //Todo: min, max, mode, median, μ, σ,


}
