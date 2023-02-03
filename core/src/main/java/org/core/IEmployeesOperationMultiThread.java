package org.core;

public interface IEmployeesOperationMultiThread {

    static void task() {
        System.out.println(Thread.currentThread() + "..start");

        for (int i = 0; i < 100; i++) {
            System.out.println(i + " " + Thread.currentThread());
        }

        System.out.println(Thread.currentThread() + "..done");
    }
}
