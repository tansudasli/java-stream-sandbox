package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class TasksThreadSafeBenchmark {
    private final static int LAST = 10000;

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(0)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
   public static void forThreadLocal() {
       ITasks.forThreadLocal.accept(LAST);
   }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(0)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void forSynchronized() {
        ITasks.forSynchronized.accept(LAST);
    }


    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(0)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static void forPerTask() {
        ITasks.forPerTask.accept(LAST);
    }

    public static void main(String[] args) throws RunnerException {

        System.out.println("..." + Thread.currentThread());

        var opt = new OptionsBuilder()
                .include(TasksThreadSafeBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .threads(2)   //increases jmh-threads, if your test is already multithreaded, it may badly affect your perf.
                .warmupIterations(1)
                .measurementIterations(2)
                .forks(1)
//                .resultFormat(ResultFormatType.JSON)
//                .result("build/".concat(org.core.TasksThreadSafeBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;

        System.out.println("..." + Thread.currentThread());

    }
}
