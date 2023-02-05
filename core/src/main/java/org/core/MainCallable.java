package org.core;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainCallable {



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread());

        ExecutorService executorService = Executors.newFixedThreadPool(2); //newSingleThreadExecutor();

        //submit, execute, invokeAll
        executorService.execute(MainCallable::taskRunnable);

        executorService.invokeAll(Arrays.asList(MainCallable::taskCallable))
                .forEach((future) -> {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });

//        System.out.println(executorService.submit(MainCallable::taskCallable).get());

        System.out.println("end");

        executorService.shutdown();

    }

    private static String taskCallable() {

        System.out.println(Thread.currentThread() + "::taskCallable");

        return "called";
    }

    private static void taskRunnable() {
        System.out.println(Thread.currentThread() + "::taskRunnable");

        System.out.println("run");
    }
}
