package org.core;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class PeopleStatisticTest {

    private static final Integer CAPACITY = IPeopleGeneratorService.of.get().size();
    private static final long SIZE = IPeopleGeneratorService.of.get()
                                                .stream().distinct()
                                                .count();

    static List<Person> people = IPeopleGeneratorService.of.get();

    @Test
    void sumOfAge(){

        //in parallelStream, results can be combined w/ another BiOperator !
        System.out.println(
                people.parallelStream()
                        .map(Person::age)
                        .reduce(0,                //identity: u = 0
                                 Integer::sum,        // (u, t) -> u + t
                                 Integer::sum));      // (u, u) -> u + u and returns u

    }

    @Test
    void meanOfAge(){
        //Todo: make better exception handling
//        Supplier<IllegalArgumentException> missingAgeException = () -> new IllegalArgumentException("age missing!");

        System.out.printf("Size=%d | Capacity=%d\n", SIZE, CAPACITY);
        System.out.printf("Age of Σ=%d | size=%d | average=%f\n", total.get(), SIZE, (double)(total.get()/CAPACITY));

    }

    //uses hashCode() and equals() which by default counts all fields!
    //or get Set<Person> to distinct
    public static Supplier<Integer> total = () -> people.parallelStream().distinct()
                .map(Person::age)
                .reduce(Integer::sum)
                .orElse(-1);

    //Todo: min, max, mode, median, μ, σ, groupByGender, histogramByAge


    /*
    gender   count
    FEMALE | 2
      MALE | 2
     */
    @Test
    void groupByGenderAndSize() {
        groupByGender.get()
                .forEach((gender, people) -> System.out.printf("%6s | %d\n", gender, people.size()));

    }

    /*
    FEMALE | List<Person>
      MALE | List<Person>
     */
    public static Supplier<Map<GENDER, List<Person>>> groupByGender =
            () -> people.parallelStream().distinct()
                    .collect(Collectors.groupingBy(Person::gender));

    @Test
    void groupByGenderAndCount() {
        groupByGenderAndCount.get()
                .forEach((gender, count) -> System.out.println(gender + " | " + count));
    }

    /*
      FEMALE | x
      MALE   | y
     */
    public static Supplier<Map<GENDER, Long>> groupByGenderAndCount =
            () -> people.parallelStream().distinct()
                    .collect(Collectors.groupingBy(Person::gender, Collectors.counting()));

    /*
    FEMALE | PersonX
    FEMALE | PersonY
    MALE   | PersonK...
     */
    public static Supplier<Map<GENDER, Person>> mapByGender =
            () -> people.parallelStream().distinct()
                    .collect(Collectors.toMap(Person::gender, person -> person));

    //Todo: groupByAge
    //Todo: groupByAgeScale
    @Test
    void mapByAge() {
        mapByAgeAndOne.get()
                .forEach((age,count) -> System.out.println(age + "|" + count));
    }

    @Test
    void groupByAgeAndCount() {
        groupByAgeAndCount.get()
                .forEach((age,count) -> System.out.println(age + "|" + count));
    }

    /*if age is not unique, map fails ! Thats why we may need groupBy !
      age       count
      30     |   x
      35     |   y
      40     |   z
     */
    public static Supplier<Map<Integer, Integer>> mapByAgeAndOne =
            () -> people.parallelStream().distinct()
                    .map(Person::age)
                    .collect(Collectors.toMap(Function.identity(), age -> 1))
                    ;

    /*if age is not unique, map fails ! Thats why we may need groupBy !
      age       count
      30     |   2
      35     |   1
      40     |   3
     */
    public static Supplier<Map<Integer, Long>> groupByAgeAndCount =
            () -> people.parallelStream()
                    .collect(Collectors.groupingBy(Person::age, Collectors.counting()))
            ;

    @Test
    void mapByAgeAndScaleThenCount() {
        mapByAgeAndScaleThenCount.apply(10)
                .forEach((age, count) -> System.out.println(age + " | " + count));
    }

    private static final BiFunction<Integer, Integer, Integer> scaleAge = (age, scale) -> (age / scale) * scale;

    /* histogram by age and
     scale = 10
      age          count
      30..39   |   x + y     [30, 40)
      40..49   |   z         [40, 50)
     */
    public static Function<Integer, Map<Integer, Long>> mapByAgeAndScaleThenCount =
            (scale) -> people.parallelStream()
                    .map(person -> scaleAge.apply(person.age(), scale))
                    .collect(Collectors.groupingBy(age -> age, Collectors.counting()));



}
