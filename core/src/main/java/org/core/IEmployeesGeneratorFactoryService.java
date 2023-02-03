package org.core;

import java.util.*;
import java.util.function.Supplier;

@FunctionalInterface
public interface IEmployeesGeneratorFactoryService {

    List<String> data = Arrays.asList("Ali", "Veli", "Deli", "Kelli", "Zilli");
    Supplier<List<String>> of = () -> data;

    static List<String> create() {
        return  data;
    }

    //functional interface
    List<String> off();
}
