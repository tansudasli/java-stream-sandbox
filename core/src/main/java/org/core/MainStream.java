package org.core;

import java.util.Optional;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ".....");

        //no thread pool, just old-fashioned another thread w/ stream usage
//        new Thread(() -> IEmployeeOperations.taskTraditionalFor(Optional.of(LAST)), "main").start();
        new Thread(() -> IEmployeeOperations.taskStream(Optional.of(ILast.LAST)), "task").start();
//        IEmployeeOperations.taskParallelStream(Optional.of(LAST));

        System.out.println(Thread.currentThread() + ".....");
    }
}
