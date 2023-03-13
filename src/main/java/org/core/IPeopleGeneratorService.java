package org.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface IPeopleGeneratorService {

    List<Person> dataMutable = Arrays.asList(
            new Person(1, "James", "Williams", "James@hotmail.com", Gender.MALE,44, Arrays.asList("1-857-545-5925","1-894-887-1309")),
            new Person(2,"Ezekiel","Haynes","ezekiel8216@icloud.net", Gender.MALE, 32, Arrays.asList("1-914-177-5177","1-286-471-5016")),
            new Person(453,"Kirestin","Stevenson","kirestin@outlook.couk", Gender.FEMALE,41,Arrays.asList("1-260-278-4847","1-368-942-5288")),
            new Person(454,"Camilla","Contreras","camilla@aol.net", Gender.FEMALE,19,Arrays.asList("1-636-524-5164","1-662-329-2646"))
    );

    //new ArrayList<>() --- mutable version
    //immutable creation
    //if it is immutable, some inplace sort methods do not work
    List<Person> data = List.of(
            //id=1 records for equality check when creating Set<Person>
            new Person(1, "James", "Williams", "James@hotmail.com", Gender.MALE,44, Arrays.asList("1-857-545-5925","1-894-887-1309")),
            new Person(1, "Xames", "Stevenson", "James@hotmail.com", Gender.MALE,44, Arrays.asList("1-857-545-5925","1-894-887-1309")),
            new Person(2,"Ezekiel","Haynes","ezekiel8216@icloud.net", Gender.MALE, 32, Arrays.asList("1-914-177-5177","1-286-471-5016")),
            new Person(453,"Kirestin","Stevenson","kirestin@outlook.couk", Gender.FEMALE,41,Arrays.asList("1-260-278-4847","1-368-942-5288")),
            new Person(454,"Camilla","Contreras","camilla@aol.net", Gender.FEMALE,19,Arrays.asList("1-636-524-5164","1-662-329-2646"))
    );

    Function<String, Person> toPerson = (line) -> {

        String regex = "(^\\d+),([\\w|ÜüığÇçÖöŞşİi̇]+),([\\w|ÜüığÇçÖöŞşİi̇|']+),(.+@.+),(M.+|F.+),([\\d|-]+),([\\d|-]+),(.+$)";
                     //  id,    name,    lastName,  email,  gender,   age,       phone,     phone1

        return Pattern.compile(regex)
                .matcher(line)
                .results()
                .map(match -> new Person(Integer.valueOf(match.group(1)),
                                         match.group(2),
                                         match.group(3),
                                         match.group(4),
                                         match.group(5).equals("Male") ? Gender.MALE : Gender.FEMALE,
                                         Integer.valueOf(match.group(6)),
                                         Arrays.asList(match.group(7), match.group(8))))
                .findFirst()
                .orElseThrow();

    };

    int FIRST_LINE_IS_HEADER = 1;
    String PATH_OF_PEOPLE_TXT = "/Users/tansudasli/coding/java-stream-sandbox/src/main/resources/people.txt";

    Supplier<List<Person>> of = () -> {
        try {
            return Files.lines(Path.of(PATH_OF_PEOPLE_TXT))
                    .skip(FIRST_LINE_IS_HEADER)
                    .map(toPerson)
                    .collect(Collectors.toList());

        } catch (IOException e) { throw new RuntimeException(e); }
    };



}
