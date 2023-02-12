package org.core;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * simulates operations/task on employee data
 */
public interface ITasks {

    static void taskStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        System.out.println(
                "t:" + IntStream.range(0, last.orElse(10))
                        .boxed()
                        .peek(i -> System.out.println(log + i))
                        .toList()
        );

        System.out.println(Thread.currentThread() + "..end");
    }

    static void taskParallelStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        IntStream.range(0, last.orElse(10))
                .boxed()
                .parallel()
//                        .peek(i -> log + i))
                .forEach(i -> System.out.println(log + i));

        System.out.println(Thread.currentThread() + "..end");
    }

    static void taskTraditionalFor(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "...begin");

        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        for (int i = 0; i < last.orElse(10); i++)
            System.out.println(log + i);

        System.out.println(Thread.currentThread() + "...end");
    }
}
