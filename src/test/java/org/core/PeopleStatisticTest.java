package org.core;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

public class PeopleStatisticTest {

    @Test
    void sumOfAge(){

        //in parallelStream, results can be combined w/ another BiOperator !
        System.out.println(
                IPeople.people
                        .parallelStream()
                        .map(Person::age)
                        .reduce(0,                //identity: u = 0
                                 Integer::sum,        // (u, t) -> u + t
                                 Integer::sum));      // (u, u) -> u + u and returns u
    }


    //uses hashCode() and equals() which by default counts all fields!
    //stream.distinct() or get Set<Person> to distinct
    public static Supplier<Integer> sumOfAge =
            () -> IPeople.people
                    .parallelStream().distinct()
                    .map(Person::age)
                    .reduce(Integer::sum)
                    .orElse(-1);

    //Todo: min,
    //      max (max value),
    //      mode (max occurred),
    //      median (middle of ),
    //      μ (mean = average),
    //      σ (std. dev.),
    //      groupBy....,
    //      histogramOf....


    @Test
    void peopleGroupByGender() {
        IPeople.peopleGroupByGender.get()
                .forEach((gender, count) -> System.out.println(gender + " | " + count));
    }

    @Test
    void peopleGroupByAge() {
        IPeople.peopleGroupByAge.get()
                .forEach((age,count) -> System.out.println(age + "|" + count));
    }

    @Test
    void histogramOfAge() {
        IPeople.histogramOfAge.apply(10)
                .forEach((age, count) -> System.out.println(age + " | " + count));
    }


    //Todo: min/mode/max/mean of age calculations


//    public static Supplier<Integer> modeOfAge =
//            () -> groupByAgeAndCount.get()
//                    .entrySet()
//                    .stream()
//                    .map(Map.Entry::getValue)
//                    .max(Comparator.naturalOrder() )
//                    .orElse(Long.getLong("-1"))
//                    .intValue();



    @Test
    void modeOfAge() {
        System.out.println(IPeople.modeOfAge.get().getKey() + " | " + IPeople.modeOfAge.get().getValue());
    }
    @Test
    void minOfAge() {
        System.out.println(IPeople.minOfAge.get());
    }
    @Test
    void maxOfAge() {
        System.out.println(IPeople.maxOfAge.get());
    }
    @Test
    void meanOfAge() {
        System.out.println(IPeople.meanOfAge.get());
    }
}
