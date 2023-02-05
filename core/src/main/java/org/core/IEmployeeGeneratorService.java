package org.core;

import java.util.*;
import java.util.function.Supplier;

/**
 * simulates sample employee data
 */
@FunctionalInterface
public interface IEmployeeGeneratorService {

    List<String> data = Arrays.asList("Ali", "Veli", "Deli", "Kelli", "Zilli");
    Supplier<List<String>> of = () -> data;

    static List<String> create() {
        return  data;
    }

    //functional interface
    List<String> off();
}
