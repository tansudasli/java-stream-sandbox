package org.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ToStreamDeepTest {
    //understanding streams

    @BeforeEach
    void before() {
        System.out.println("start::: ".concat(Thread.currentThread().getName()));
    }

    @Test
    void test() {
        emailsZ.get()
                .forEach(s -> {
                    System.out.println("forEach::: "
                                       .concat(Thread.currentThread().getName())
                                       .concat("::: ")
                                       .concat(s));

                });
    }

    /* Understanding thread allocations of parallelStream
     - not parallel, same thread, gets a person then completes all steps.

       collect::new runs 1 times

       person 1 map -> collect::add
       person 2 map -> collect::add

       @last step, foreach steps

     - parallel, leverages multi threads, and all steps can be happen

       collect::new -> collect::map -> collect::new -> collect::map -> collect::add .....

       @last step, foreach steps

     */
    Supplier<List<String>> emailsZ =
            () -> IPeople.people
                    .stream()
                    .parallel()
                    .map(person -> {
                        System.out.println("map::: ".concat(Thread.currentThread().getName()));

                        return person.email();
                    })
                    .collect(() -> {
                                System.out.println("collect:::new ".concat(Thread.currentThread().getName()));

                                return new ArrayList<>();
                             }, //ArrayList::new
                             (list, email) -> {
                                System.out.println("collect:::add ".concat(Thread.currentThread().getName())
                                                                   .concat(" ::: " + email));

                                list.add(email);
                             },   //ArrayList::add
                             ArrayList::addAll
                    );

    //short form, of emailZ
//    Supplier<List<String>> emailsZZ = () -> IPeople.people
//                                                .parallelStream()
//                                                .map(Person::email)
//                                                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

    //Todo: Collectors in detail
}
