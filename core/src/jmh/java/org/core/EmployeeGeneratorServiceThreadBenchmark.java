package org.core;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class EmployeeGeneratorServiceThreadBenchmark {

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

    /**<b>Thread Concepts</b> [create -> start ---> join]
     *  <li>pooling</li>
     *  <li>accessing same field :: locking (synchronized), Atomic Ops.</li>
     *  <li>Inter communication :: accessing respectively (producer-consumer) :: (wait & notify)</li>
     *  <li>flow (join, sleep)</li>
     *  <li>strategy :: how to define the problem for multi-threading</li>
     *  <li>computations vs IO</li>
     * <p>
     *
     * <p><p>
     *     :::::::::::::::::::::::::::::::::::::::::::::::::::::
     *  Runnable         :: 1995     -> thread = task
     *
     *
     *  new Thread(Class::task).start();
     *
     * <p><p>
     *     :::::::::::::::::::::::::::::::::::::::::::::::::::::
     *  <T> Callable<T>  :: 2004     -> pooling for better thread management
     *
     *  - better @ thread creation via pooling than new Thread().....
     *  - We can use executors for Callable & also, for Runnable interfaces!
     *  - Callable returns something!
     *  - Runnable is void!
     *  <p>
     *   executorService = Executors.newFixedThreadPool(n)
     *   executorService.submit(Class::task).get()   -> returns Future
     *
     *   .submit()
     *   .execute()
     *   .invokeAll()
     *
     *   executorService.shutdown()
     *
     * <p><p>
     *     :::::::::::::::::::::::::::::::::::::::::::::::::::::
     *  Fork/Join        :: 2011     -> dividing into smart subtasks & merging for parallel programming
     *
     *  pool = new ForkJoinPool()      //a kind of Executors! ... ForkJoinPool.commonPool()
     *  pool.execute(task)       void (async), fire & forget
     *  pool.invoke(task)        waits, then returns result immediately (sync),
     *  pool.submit(task).get()  waits, then returns Future (async)
     * <p>
     *  subtask = ...
     *  subTask.fork()
     *  subTask.join() or subTask.invoke() or invokeAll()
     * <p>
     *               Future<T>
     *                  |
     *             ForkJoinTask<T>
     *         |                   |
     *  RecursiveAction        RecursiveTask<T>
     *  (void)
     *
     * <p><p>
     *     :::::::::::::::::::::::::::::::::::::::::::::::::::::
     *  parallelStreams  :: 2011     -> parallel programming, processing in-memory data, mostly non-blocking,
     *
     *    - uses ForkJoinPool.commonPool() behind the scenes!
     *
     *
     *
     * <p><p>
     *     :::::::::::::::::::::::::::::::::::::::::::::::::::::
     *  CompletableFuture :: 2014     -> async computations & trigger dependant computations
     *
     *   - better @ functional programming than ForkJoinPool & parallelStreams
     *   - better @ basic exceptional cases than ForkJoinPool
     *   - uses ForkJoinPool.commonPool() behind the scenes!
     *   <p>
     *
     *   <p>where t1,t2.. are dependent tasks of T
     *   <p>
     *   T ... T ...  T ... T            :::independent Tasks
     *   :
     *  t1    t1
     *   :
     *  t2    t2
     *   :
     *  t2
     *
     *
     * CompletableFuture.supplyAsync(::getT1)
     *             .thenApply(::getT2)
     *             .exceptionally(e -> new handleTException(e))
     *             .
     * if you use your custom pool use thenApplyAsync -> .thenApplySync(::getTx, ioPool)
     * for more complex exception handling use -> .thenCombine(........)
     *
     *
     * <p><p>
     *     :::::::::::::::::::::::::::::::::::::::::::::::::::::
     *  reactiveStreams   :: 2015     ->
     *
     *   - better @ complex exception handling than CompletableFuture
     *
     *
     *
     * <p><p>
     *     :::::::::::::::::::::::::::::::::::::::::::::::::::::
     *  Project loom :: 2022          ->  lightweight thread == task, handling numerous blocking requests/responses
     *
     *  - jdk19 preview
     *  - lightweight thread == task,  no way to cut this bound!!
     *  - creating 1m thread {now, it costs 2tb ram, 20min startup time & context switching}
     *  - CompletionState/CompletableFuture
     *  - async -> reactive programming
     *  - Mono/Multi (spring framework)
     *  Problem is callback hell, hard to test & profile & debug where Loom is to rescue :)
     * <p>
     *  So, threads will be two types :: platform or virtual
     *
     */


    public static void main(String[] args) throws RunnerException {

        /* jmh multi threading
         *   .threads(n) defines thread count. But JMH runs test sequentially.
         * So we can only multithreading One function at a time.
         *      So, every function must be divided into smart tasks !
         */
        System.out.println("..." + Thread.currentThread());

        var opt = new OptionsBuilder()
                .include(EmployeeGeneratorServiceThreadBenchmark.class.getName())
                .jvmArgs("-Xms1g", "-Xmx1g", "-XX:+UseG1GC")
                .threads(2)
                .warmupIterations(1)
                .measurementIterations(1)
                .forks(1)
//                .resultFormat(ResultFormatType.JSON)
//                .result("build/".concat(org.core.EmployeeGeneratorServiceThreadBenchmark.class.getName()).concat(".json"))
                .build() ;

        new Runner(opt).run() ;

        System.out.println("..." + Thread.currentThread());

    }
}
