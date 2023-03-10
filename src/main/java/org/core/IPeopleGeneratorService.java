package org.core;

import java.util.*;
import java.util.function.Supplier;

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

    Supplier<List<Person>> of = () -> data;

    //Todo: read people.txt file & construct People objects + maybe a prototype pattern ?


}
