package org.core.concurrency;

import org.core.IPeople;
import org.core.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ParallelStreamThreadingTest {
    //understanding threading in java-streams

    @BeforeEach
    void before() {
        System.out.println("start::: ".concat(Thread.currentThread().getName()));
    }

    @Test
    void test() {
        emailsZ.accept(IPeople.people);
    }

    /* Understanding thread allocations of Java-Streams

     - if it is not parallel, same thread, sequential (beginning2end), gets a person then completes all-intermediate-steps.
       And, repeats the same as person and other tasks

        1 thread

            1 time  (so no need for combiner)  !! no need combiner (never enters addAll step)
               collect:::new

            N times
                      sequentially
               map:::      ->      collect:::add

            N times, last step. terminal ops. sequential
               forEach:::

     - If it is parallel, leverages multi-threads, and all intermediate-steps can be happened in any order!.
     So, combiner is must!.

       N thread
           N times
               collect::new (no optimization, which is interesting, that's why we need combiner!) -> collect::map
               -> collect::new  -> collect::new  -> collect::map
               -> collect::add ......... -> collect::addAll
               -> collect::new ......

       1 Thread
            @last step, foreach steps,  (terminal operation), sequential

     */
    Consumer<List<Person>> emailsZ =
            (people) -> people.parallelStream()
                    .map(person -> {
                        //Todo: make it async :) What happens
                        System.out.println("map::: ".concat(Thread.currentThread().getName())
                                                    .concat(" ::: " + person.email()));

                        return person.email();
                    })
                    .collect(
                            () -> {
                                System.out.println("collect:::new ".concat(Thread.currentThread().getName()));

                                return new ArrayList<>(1);
                            },
                            (list, email) -> {
                                list.add(email);

                                System.out.println("collect:::add ".concat(Thread.currentThread().getName())
                                                                   .concat(" ::: " + email)
                                                                   .concat(" ::: " + list.size()));

                            },
                            (list1, list2) -> {
                                System.out.println("collect:::addAll ".concat(Thread.currentThread().getName())
                                        .concat(" ::: " + list1.size())
                                        .concat(" ::: " + list2.size()));

                                list1.addAll(list2);
                            }
                    )
                    .forEach(email -> {
                                System.out.println("forEach::: ".concat(Thread.currentThread().getName())
                                                                .concat("::: " + email));
                            });

    //short form, of emailZ
//    Supplier<List<String>> emailsZZ = () -> IPeople.people
//                                                .parallelStream()
//                                                .map(Person::email)
//                                                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

}
