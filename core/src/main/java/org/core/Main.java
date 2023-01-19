package org.core;

import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        EmployeesGeneratorFactoryService.create()
                .stream()
                .forEach(System.out::println);

        Stream.of(EmployeesGeneratorFactoryService.create())
                .forEach(System.out::println);

        //predicate bool
        //consumer void, input
        //supplier
        //function in, out
//        Function generator = Arrays::asList;

    }
}