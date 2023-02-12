package org.core;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.function.Supplier;

class TaskTraditionalFor extends RecursiveAction {

    /*
     let's assume we will count to 1m. we will split into smart task.
    To do splitting, we need to define either counter mechanism or the thread count!
     Then, at compute() method, we will
     */
    int base = 10, last = 10, first = 0;
    private Supplier<Integer> calculateThreadCount = () -> 2;

    public TaskTraditionalFor(int base, int last, int first) {
        this.base = base;
        this.last = last;
        this.first = first;
    }

    private void base() {

        for (int i = first; i < base; i++)
            System.out.println("b: " +
                    Thread.currentThread().getName() + ":" + i);
    }



    /**
     * First                                          Last<p>
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

                TaskTraditionalFor t1 = new TaskTraditionalFor(last/threadCount, last/threadCount,  first);
                TaskTraditionalFor t2 = new TaskTraditionalFor(last, last,  last/threadCount);

                invokeAll(t1, t2);
            }
            default -> base();
        }
    }
}
public class MainFork {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        ForkJoinPool.commonPool()
//                .submit(IEmployeeOperations::taskTraditionalFor)
//                .get();
        System.out.println("sss");
//        TaskTraditionalFor taskTraditionalFor = new TaskTraditionalFor();

//        new ForkJoinPool().execute(new TaskTraditionalFor());          //execute = void
//        new ForkJoinPool().execute(new TaskTraditionalFor(5, 10, 0));
//        ForkJoinPool.commonPool().execute(new TaskTraditionalFor(5, 100, 0));
        new ForkJoinPool().invoke(new TaskTraditionalFor(5, 100, 0));           //invoke = sync
//        ForkJoinPool.commonPool().invoke(new TaskTraditionalFor());    //commonPool

        Thread.sleep(5000);

    }



}
