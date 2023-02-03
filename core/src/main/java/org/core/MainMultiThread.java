package org.core;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MainMultiThread {

    static void taskMain() {
        System.out.println(Thread.currentThread() + "...begin");

        System.out.println(
                "m:" + Arrays.toString(IntStream.range(0, 10)
                             .peek(i -> System.out.println(Thread.currentThread().getName() + ":" + i))
                             .toArray()
                           ));

        System.out.println(Thread.currentThread() + "...end");
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ".....");

        new Thread(MainMultiThread::taskMain, "main").start();

        new Thread(IEmployeesOperationMultiThread::task, "task").start();

        System.out.println(Thread.currentThread() + ".....");
    }
}
