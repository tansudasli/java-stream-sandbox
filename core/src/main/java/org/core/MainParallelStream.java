package org.core;

import java.util.Optional;

public class MainParallelStream {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ".....");

//        new Thread(() -> IEmployeeOperations.taskTraditionalFor(Optional.of(LAST)), "main").start();
//        new Thread(() -> IEmployeeOperations.taskStream(Optional.of(LAST)), "task").start();
        IEmployeeOperations.taskParallelStream(Optional.of(ILast.LAST));

        System.out.println(Thread.currentThread() + ".....");
    }
}
