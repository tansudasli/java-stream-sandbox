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
        System.out.println("start:::".concat(Thread.currentThread().getName()));
    }

    @Test
    void test() {
        emailsZ.get()
                .forEach(s -> {
                    System.out.println("forEach:::"
                                       .concat(Thread.currentThread().getName())
                                       .concat("::: ")
                                       .concat(s));

                });
    }

    Supplier<List<String>> emailsZ =
            () -> { return
                IPeople.people
                        .parallelStream()
                        .map(person -> {
                            System.out.println("map:::".concat(Thread.currentThread().getName()));

                            return person.email();
                        })
                        .collect(ArrayList::new,
                                 ArrayList::add,
                                 ArrayList::addAll);
            };

    //Todo: Collectors in detail
}
