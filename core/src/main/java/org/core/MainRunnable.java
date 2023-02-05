package org.core;

public class MainRunnable {



    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + ".....");

        new Thread(IEmployeeOperations::taskTraditionalFor, "main").start();
        new Thread(IEmployeeOperations::taskStream, "task").start();

        System.out.println(Thread.currentThread() + ".....");
    }
}
