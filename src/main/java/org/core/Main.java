package org.core;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        IPeopleGeneratorService.of.get().forEach(System.out::println);

        //predicate boolOutput
        //consumer  void, input
        //supplier  output
        //function in, out
        //functional interface
        //Function generator = Arrays::asList;

    }
}