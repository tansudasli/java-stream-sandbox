package org.core;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface IPeople {

    List<Person> people = IPeopleGeneratorService.of.get();

    //Todo: experience Runnable sort method
    Runnable peopleSortedByAge =
            () ->  people.sort(Comparator.comparingInt(Person::age));

    Supplier<List<Person>> peopleSortedByAge2 = () ->  people.stream()
                    .sorted(Comparator.comparingInt(Person::age))
                    .toList();


    /*
      FEMALE | List<Person>
      MALE   | List<Person>
    */
    Supplier<Map<GENDER, List<Person>>> peopleListGroupByGender =
            () -> people
                    .parallelStream().distinct()
                    .collect(Collectors.groupingBy(Person::gender));

    /*
      FEMALE | x
      MALE   | y
    */
    Supplier<Map<GENDER, Long>> peopleGroupByGender =
            () -> people
                    .parallelStream().distinct()
                    .collect(Collectors.groupingBy(Person::gender, Collectors.counting()));

    /*if age is not unique, map fails ! but groupBy works
      age       count
      30     |   2
      35     |   1
      40     |   3
    */
    Supplier<Map<Integer, Long>> peopleGroupByAge =
            () -> people
                    .parallelStream()
                    .collect(Collectors.groupingBy(Person::age, Collectors.counting()));


    BiFunction<Integer, Integer, Integer> scaleAge = (age, scale) -> (age / scale) * scale;

    /* histogram by age

     scale = 10
      age        scaled     count
      30..39   |  30      |  x + y     [30, 40)
      40..49   |  40      |  z         [40, 50)
     */
    Function<Integer, Map<Integer, Long>> histogramOfAge =
            (scale) -> people
                    .parallelStream()
                    .map(person -> scaleAge.apply(person.age(), scale))
                    .collect(Collectors.groupingBy(age -> age, Collectors.counting()));


    /*
       Map<age, count>  - find max
      age       count    mode
      30     |   2     |
      35     |   1     |
      40     |   3     |  x
    */
    Supplier<Map.Entry<Integer, Long>> modeOfAge =
            () -> peopleGroupByAge.get().entrySet()
                    .stream()
                    .max(Comparator.comparingLong(Map.Entry::getValue))
                    .orElseThrow();

//    old style
//    Supplier<Map.Entry<Integer, Long>> modeOfAge = () -> {
//        Map.Entry<Integer, Long> maxOccurring = null;
//
//        for ( Map.Entry<Integer, Long> entry : peopleGroupByAge.get().entrySet() ) {
//            if (maxOccurring == null) maxOccurring = entry;
//
//            maxOccurring = maxOccurring.getValue() > entry.getValue() ? maxOccurring : entry;
//        }
//
//        return maxOccurring;
//    };

    Supplier<Integer> minOfAge =
            () -> people.stream()
                    .min(Comparator.comparingInt(Person::age))
                    .orElseThrow()
                    .age();

    /*
    finding max in 3 ways

    1- .max((p1, p2) -> p1.age() - p2.age())
    2- .max(Comparator.comparingInt(Person::age))
    3- .map(Person::age)
       .max(Comparator.naturalOrder())
     */
    Supplier<Integer> maxOfAge =
            () -> people.stream()
//                    .max((p1, p2) -> p1.age() - p2.age())           //the expected way
//                    .max(Comparator.comparingInt(Person::age))
                    .map(Person::age)
                    .max(Comparator.naturalOrder())                 //expects T, so we need map() for this case
                    .orElseThrow()
//                    .age()
            ;

    /* 2 way is possible..
      1- person -> .mapToDouble(person::age) -> .average()
      2- using Collectors..
     */
    Supplier<Double> meanOfAge =
            () -> people.stream()
                    .distinct()
                    .collect(Collectors.averagingDouble(Person::age));

    /*

   person    age     sort      middle
   personX |  30    | ..X    |
   personY |  40    | ..Z    |   ..Z
   personZ |  35    | ..Y    |
     */
    double SIZE = people.stream().distinct().count();
    int INDEX = SIZE % 2 == 0 ? (int)SIZE / 2 - 1 : (int) Math.round( SIZE / 2.0) - 1;

    //if SIZE is even, two numbers must be averaged!
    Supplier<Double> medianOfAge =
            () -> switch ((int) (SIZE % 2)) {
        //SIZE is even, two numbers must be averaged
                case 0 -> people.stream()
                        .distinct()
                        .sorted(Comparator.comparingInt(Person::age))
                        .toList()
                        .subList(INDEX, INDEX + 2)
                        .stream()
                        .collect(Collectors.averagingDouble(Person::age));
        //SIZE is odd. get one number
                default -> people.stream()
                        .distinct()
                        .sorted(Comparator.comparingInt(Person::age))
                        .toList()
                        .get(INDEX)
                        .age()
                        .doubleValue();
             };


}
