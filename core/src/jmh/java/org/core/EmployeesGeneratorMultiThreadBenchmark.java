package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class EmployeesGeneratorMultiThreadBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @Fork(1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void task1() {
        System.out.println(Thread.currentThread());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 0)
    @Measurement(iterations = 1)
    @Fork(1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void task2() {
        System.out.println(Thread.currentThread());
    }

    /** Threads [create -> start ---> join]
     *  - pooling
     *  - accessing same field :: locking (synchronized), Atomic Ops.
     *  - Inter communication :: accessing respectively (producer-consumer) :: wait & notify
     *  - flow (sleep, switching ...)
     *  - strategy :: how to define problem for multi-threading
     *  - computations vs IO
     * <p>
     *
     *  Runnable interface :: 1995
     *  Callable :: 2004
     *  Fork/Join :: 2011           -> dividing into smart subtasks & merging for parallel programming
     *  parallelStreams :: 2011     -> parallel programming, processing in-memory data, mostly non-blocking
     *  CompletableFuture :: 2014
     * <p>
     *  Project loom :: 2022 java19 preview ->  handling numerous blocking requests/responses
     *     thread == task no way to cut this bound!!
     *     creating 1m thread {now, costs 2tb ram, 20min startup time & context switching}
     *  - CompletionState/CompletableFuture
     *  - async -> reactive programming
     *  - Mono/Multi (spring framework)
     *  Problem is callback hell, hard to test & profile & debug where Loom is to rescue :)
     * <p>
     *  So, threads are two types now :: platform or virtual
     *
     */


    public static void main(String[] args) throws RunnerException {
//        Main.main(args);
        System.out.println("...");

//        new Thread(EmployeesGeneratorMultiThreadBenchmark::task1).start();

        var opt = new OptionsBuilder()
                .include(org.core.EmployeesGeneratorMultiThreadBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .threads(2)
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
//                .resultFormat(ResultFormatType.JSON)
//                .result("build/".concat(org.core.EmployeesGeneratorMultiThreadBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;

//        new Thread(() -> {
//            try {
//                new Runner(opt).run();
//
//            } catch (RunnerException e) { throw new RuntimeException(e); }
//        }).start();
    }
}
