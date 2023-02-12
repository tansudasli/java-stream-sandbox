package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class EmployeeOperationsBenchmark {

    private final static int LAST = 1000;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 0)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testTaskTraditionalForSingleThread() {
        IEmployeeOperations.taskTraditionalFor(Optional.of(LAST));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 0)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testTaskTraditionalForMultiThread() {
        MainFork.spark.accept(LAST);

//        Thread.sleep(1000);
    }

    public static void main(String[] args) throws RunnerException, InterruptedException {

        /* jmh multi threading
         *   .threads(n) defines thread count. But JMH runs test sequentially.
         * So we can only multithreading One function at a time.
         *      So, every function must be divided into smart tasks !
         */
        System.out.println("..." + Thread.currentThread());

        var opt = new OptionsBuilder()
                .include(EmployeeOperationsBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .threads(2)
                .warmupIterations(0)
                .measurementIterations(1)
                .forks(0)
//                .resultFormat(ResultFormatType.JSON)
//                .result("build/".concat(org.core.EmployeeOperationsBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;

        Thread.sleep(5000);

        System.out.println("..." + Thread.currentThread());

    }
}
