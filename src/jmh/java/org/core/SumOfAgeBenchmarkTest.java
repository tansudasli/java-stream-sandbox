package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class SumOfAgeBenchmarkTest {

    //tests sum of age values by parallelStream vs stream

    public static Function<List<Person>, Integer>
            totalSequentially = (people) -> people.stream().distinct()
                                                .map(Person::age)
                                                .reduce(Integer::sum)
                                                .orElse(-1);

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @Fork(1)
    @Threads(2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void inParallel() {
        StatisticsTest.sumOfAge.get();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 2)
    @Fork(1)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void inSequential() {
        totalSequentially.apply(IPeopleGeneratorService.of.get());
    }

    /* to run,

     - ./gradlew   clean build jar jmhJar
     - java -cp build/libs/java-stream-sandbox-1.0-SNAPSHOT-jmh.jar org.core.SumOfAgeBenchmarkTest

     */
    public static void main(String[] args) throws RunnerException {

        /* jmh multi threading
         *   .threads(n) defines thread count. But JMH runs test sequentially.
         * So we can only multithreading One function at a time.
         *      So, every function must be divided into smart tasks !
         */
        System.out.println("..." + Thread.currentThread());

        var opt = new OptionsBuilder()
                .include(SumOfAgeBenchmarkTest.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
//                .threads(2)   //increases jmh-threads, if your test is already multithreaded, it may badly affect your perf.
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
                .build() ;

        new Runner(opt).run() ;

        System.out.println("..." + Thread.currentThread());

    }

    //people size = 5k
//    Benchmark                           Mode  Cnt      Score   Error  Units
//    SumOfAgeBenchmarkTest.inParallel    avgt         978.995          us/op
//    SumOfAgeBenchmarkTest.inSequential  avgt       27810.961          us/op

    //people size = 8k
//    Benchmark                           Mode  Cnt      Score   Error  Units
//    SumOfAgeBenchmarkTest.inParallel    avgt        1308.693          us/op
//    SumOfAgeBenchmarkTest.inSequential  avgt       44357.695          us/op
}
