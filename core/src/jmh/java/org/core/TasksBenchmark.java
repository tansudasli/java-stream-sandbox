package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class TasksBenchmark {

    private final static int LAST = 1000000;
    private final static int FIRST = 0;

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testTaskTraditionalForSingleThread() {
        ITasks.taskTraditionalFor(Optional.of(LAST));
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(0)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void testTaskTraditionalForMultiThread() {
        MainFork.spark.accept(LAST, FIRST);
    }

    public static void main(String[] args) throws RunnerException {

        /* jmh multi threading
         *   .threads(n) defines thread count. But JMH runs test sequentially.
         * So we can only multithreading One function at a time.
         *      So, every function must be divided into smart tasks !
         */
        System.out.println("..." + Thread.currentThread());

        var opt = new OptionsBuilder()
                .include(TasksBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
//                .threads(2)   //increases jmh-threads, if your test is already multithreaded, it may badly affect your perf.
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
//                .resultFormat(ResultFormatType.JSON)
//                .result("build/".concat(org.core.TasksBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;

        System.out.println("..." + Thread.currentThread());

    }

    //last = 1m
    //Benchmark                                           Mode  Cnt      Score   Error   Units
    //TasksBenchmark.testTaskTraditionalForMultiThread   thrpt          ≈ 10⁻⁴          ops/ms
    //TasksBenchmark.testTaskTraditionalForSingleThread  thrpt          ≈ 10⁻⁴          ops/ms
    //TasksBenchmark.testTaskTraditionalForMultiThread    avgt       12491.590           ms/op
    //TasksBenchmark.testTaskTraditionalForSingleThread   avgt       11827.027           ms/op
}
