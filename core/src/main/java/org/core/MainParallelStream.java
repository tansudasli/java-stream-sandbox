package org.core;

import java.util.Optional;

public class MainParallelStream {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ".....");

//        new Thread(() -> ITasks.taskTraditionalFor(Optional.of(LAST)), "main").start();
//        new Thread(() -> ITasks.taskStream(Optional.of(LAST)), "task").start();
        ITasks.taskParallelStream(Optional.of(ILast.LAST));

        System.out.println(Thread.currentThread() + ".....");
    }
}
