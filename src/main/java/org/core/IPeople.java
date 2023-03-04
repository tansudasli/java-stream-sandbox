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
            () -> { return 0; };

    Supplier<Integer> maxOfAge =
            () -> { return 0; };

    Supplier<Integer> meanOfAge =
            () -> { return 0; };

    Supplier<Integer> medianOfAge =
            () -> { return 0; };


}
