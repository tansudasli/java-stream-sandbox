package org.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@FunctionalInterface
public interface EmployeesGeneratorFactoryService {

    List<String> of();

    static List<String> create() {
        return  Arrays.asList("Ali", "Veli", "Deli", "Kelli", "Zilli",
                "Mali", "Tai", "Kali", "Kelile", "Dimme", "Zimmi", "Cilli", "Sari", "MAri",
                "Dari" , "VArik", "Karik", "Guduk", "Zubuk");
    }
}
