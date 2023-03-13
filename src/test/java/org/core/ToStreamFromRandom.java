package org.core;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

public class ToStreamFromRandom {

    @Test
    void generateTest() {

        /*
          random numbers b/w 1...7
          10 numbers
         */
        new SecureRandom()
                .ints(10, 1, 7)
                .mapToObj(String::valueOf)
                .forEach(System.out::println);
    }
}
