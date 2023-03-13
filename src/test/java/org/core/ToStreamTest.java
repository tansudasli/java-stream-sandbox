package org.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ToStreamTest {

    void x() {
    Stream.of(new Object(), new Object())
            .forEach(System.out::println);
    new ArrayList<>() {{ add(2);
                         add(3); }}
            .stream()
            .forEach(System.out::println);

        new ArrayList<>(Arrays.asList(1, 3, 4))
                .stream()
                .forEach(System.out::println);

    }


    //Todo: java fundamentals MapX, QueueX ... How can we handle these topics ?

}
