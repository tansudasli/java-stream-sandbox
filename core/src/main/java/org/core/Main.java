package org.core;

import java.util.List;

public class Main {

    static List<String> employees() {
        return EmployeesGeneratorFactoryService.create();
    }

    public static void main(String[] args) {
//        System.out.println("Hello world!");

        employees().stream()
                .forEach(System.out::println);

//        Stream.of(employees())
//                .forEach(System.out::println);

        //predicate bool
        //consumer void, input
        //supplier
        //function in, out
//        Function generator = Arrays::asList;

    }
}