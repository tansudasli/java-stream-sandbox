package org.example;

import org.core.EmployeesGeneratorFactoryService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println(new Fp(5).getI());

        //access lib project core
        EmployeesGeneratorFactoryService.create()
                .stream()
                .forEach(System.out::println);

    }
}