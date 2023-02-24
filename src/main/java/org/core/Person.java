package org.core;

import java.util.List;

/**
 * immutable
 * hashcode, equals generated by default
 * we can inject methods
 * no non-static things
 * no extend class, cannot be extended
 * implements interfaces
 * compact constructor is possible, if you need some validations etc...
 */
public record Person(Integer id, String firstName, String lastname, String email, String gender, Integer age, List<String> phones) {

    //compact constructor
    public Person {
        //Todo: validate using function chaining (combining pattern) - high order functions
        if (id < 0) throw new IllegalArgumentException("not valid person id!");
    }
}
