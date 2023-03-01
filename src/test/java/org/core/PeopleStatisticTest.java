package org.core;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Supplier;

public class PeopleStatisticTest {

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
    void averageOfAge(){
        Supplier<IllegalArgumentException> missingAgeException = () -> new IllegalArgumentException("age missing!");

        var total = people.stream()
                .map(Person::age)
                .reduce(Integer::sum)
                .orElseThrow(missingAgeException);

        var count = (long) people.size();

        System.out.printf("Age of Σ=%d | size=%d | average=%f\n", total, count, (double)(total/count));

    }


}
