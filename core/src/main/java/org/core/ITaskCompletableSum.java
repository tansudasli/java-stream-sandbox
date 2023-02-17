package org.core;

import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ITaskCompletableSum {

    private static final int slice = 2;

    public static Supplier<Integer> sum  = () -> IntStream.range(0, ILast.LAST).sum();

    public static Supplier<Integer> sum1 =
            () -> IntStream.range(0, ILast.LAST/slice)
                           .peek(i -> System.out.println("sum1: " + ITasks.logContextHolder.get() + i ))
                           .sum();

    public static Supplier<Integer> sum2 =
            () -> IntStream.range(ILast.LAST/slice, ILast.LAST)
                    .peek(i -> System.out.println("sum1: " + ITasks.logContextHolder.get() + i ))
                    .sum();

}
