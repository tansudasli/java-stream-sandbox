package org.core;


import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.function.Consumer;
import java.util.function.Supplier;

class TaskTraditionalFor extends RecursiveAction {

    /*
     let's assume we will count to 1m. we will split into smart task.
    To do splitting, we need to define either counter mechanism or the thread count!
     Then, at compute() method, we will
     */
    int base, last, first;
    private final Supplier<Integer> calculateThreadCount = () -> 2;

    public TaskTraditionalFor(Optional<Integer> base, Optional<Integer> last, Optional<Integer> first) {
        this.base = base.orElse(last.orElse(10) / 2);
        this.last = last.orElse(10);
        this.first = first.orElse(0);
    }

    private void base() {
        String log = ILast.log ? Thread.currentThread().getName() + ": "  : "";

        for (int i = first; i < base; i++)
            System.out.println(log + i);
    }

    /**
     * First                                          ILast<p>
     * 0.........base.................................10000
     * <p>
     * <p>at main body, invoke/execute or submit big-chunky-Task
     * <p> - create a Task class that extends RecursiveTask or RecursiveAction (void)
     * <p> - define a Base method
     * <p> - divide into sub-tasks that has own Base method, then invoke/fork or join the sub-tasks again
     */

    @Override
    protected void compute() {

        switch (Integer.signum(last - base)) {
            case 1  -> {
                //Todo: create separate tasks | slice big task into small tasks then invoke, execute, submit etc..
                int threadCount = calculateThreadCount.get();

                TaskTraditionalFor t1 = new TaskTraditionalFor(Optional.of(last/threadCount),
                                                                Optional.of(last/threadCount),
                                                                Optional.of(first));
                TaskTraditionalFor t2 = new TaskTraditionalFor(Optional.of(last),
                                                                Optional.of(last),
                                                                Optional.of(last/threadCount));

                invokeAll(t1, t2);
            }
            default -> base();
        }
    }
}
public class MainFork implements ILast {

    //invoke waits the result
    public static Consumer<Integer> spark = (last) -> ForkJoinPool.commonPool()
                                                     .invoke(new
                                                             TaskTraditionalFor(Optional.of(last/2),
                                                                                Optional.of(last),
                                                                                Optional.of(0)));

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        ForkJoinPool.commonPool()
//                .submit(ITasks::taskTraditionalFor)
//                .get();
        System.out.println("sss");
//        TaskTraditionalFor taskTraditionalFor = new TaskTraditionalFor();
//        new ForkJoinPool().execute(new TaskTraditionalFor());          //execute = void
//        new ForkJoinPool().execute(new TaskTraditionalFor(5, 10, 0));
//        ForkJoinPool.commonPool().execute(new TaskTraditionalFor(500, 1000, 0));
//        ForkJoinPool.commonPool().invoke(new TaskTraditionalFor());    //commonPool

        spark.accept(LAST);

//        Thread.sleep(5000);

    }



}
