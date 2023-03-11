package org.core.concurrency;

import org.core.IPeople;
import org.core.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StreamThreadingTest {
    //understanding threading in java-streams

    @BeforeEach
    void before() {
        System.out.println("start::: ".concat(Thread.currentThread().getName()));
    }

    @Test
    void test() {
        emailsZ.accept(IPeople.people);
    }

    Consumer<List<Person>> emailsZ =
            (people) -> people.stream()
                    .map(person -> {
                        //Todo: make it async :) What happens
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

}
