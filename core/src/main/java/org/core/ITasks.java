package org.core;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * simulates operations/task on employee data
 */
public interface ITasks {

    /**
     * When we make below line shared by all threads, it gets crazy :)
     *
     * 1- not thread-safe  :: lock & synchronized
     * 2- use thread-safe version
     * 3- 1 obj = 1 thread :: ThreadLocal<>
     * 4- 1 obj = 1 task   :: too much memory, expensive
     */
     static String getLog() {
        String log;

        synchronized (ITasks.class) {
            log = ILast.log ? Thread.currentThread().getName() + ": " : "";
        }

        return log;
     }

     Supplier<String> log  = () -> ILast.log ? Thread.currentThread().getName() + ": " : "";

     static void taskStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        System.out.println(
                "t:" + IntStream.range(0, last.orElse(10))
                        .boxed()
                        .peek(i -> System.out.println(log + i))
                        .toList()
        );

        System.out.println(Thread.currentThread() + "..end");
     }
     static void taskParallelStream(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "..begin");

        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        IntStream.range(0, last.orElse(10))
                .boxed()
                .parallel()
//                        .peek(i -> log + i))
                .forEach(i -> System.out.println(log + i));

        System.out.println(Thread.currentThread() + "..end");
     }

     static void taskTraditionalFor(Optional<Integer> last) {
        System.out.println(Thread.currentThread() + "...begin");

//        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

            for (int i = 0; i < last.orElse(10); i++)
                synchronized (ITasks.class) { System.out.println(log.get() + i); }

        System.out.println(Thread.currentThread() + "...end");
     }
}
