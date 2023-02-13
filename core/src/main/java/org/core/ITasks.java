package org.core;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * simulates operations/task on employee data
 */
public interface ITasks {

    //perf optimization
    String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

    static void taskStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        System.out.println(
                IntStream.range(0, last.orElse(10))
                        .boxed()
                        .peek(i -> System.out.println(log + i))
                        .toList()
        );

        System.out.println(Thread.currentThread() + "..end");
    }

    static void taskParallelStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        IntStream.range(0, last.orElse(10))
                .boxed()
                .parallel()
//                        .peek(i -> log + i))
                .forEach(i -> System.out.println(log + i));

        System.out.println(Thread.currentThread() + "..end");
    }

    static void taskTraditionalFor(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "...begin");

        for (int i = 0; i < last.orElse(10); i++)
            System.out.println(log + i);

        System.out.println(Thread.currentThread() + "...end");
    }
}
