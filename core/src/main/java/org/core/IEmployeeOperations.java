package org.core;

import java.util.stream.IntStream;

/**
 * simulates operations/task on employee data
 */
public interface IEmployeeOperations {

    static void taskStream() {
        System.out.println(Thread.currentThread() + "..begin");

        System.out.println(
                "t:" + IntStream.range(0, 10)
                        .boxed()
                        .peek(i -> System.out.println(Thread.currentThread().getName() + ":: "+ i))
                        .toList()
        );

        System.out.println(Thread.currentThread() + "..end");
    }

    static void taskParallelStream() {
        System.out.println(Thread.currentThread() + "..begin");

        System.out.println(
                "t:" + IntStream.range(0, 10)
                        .boxed()
                        .parallel()
                        .peek(i -> System.out.println(Thread.currentThread().getName() + ":: "+ i))
                        .toList()
        );

        System.out.println(Thread.currentThread() + "..end");
    }

    static void taskTraditionalFor() {
        System.out.println(Thread.currentThread() + "...begin");

        for (int i = 0; i < 10; i++)
            System.out.println("m:" +
                               Thread.currentThread().getName() + ":" + i);

        System.out.println(Thread.currentThread() + "...end");
    }
}
