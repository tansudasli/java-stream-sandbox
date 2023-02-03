package org.core;

import java.util.stream.IntStream;

public interface IEmployeesOperationMultiThread {

    static void task() {
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
}
