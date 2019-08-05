package com.waytoodanny.examples.schedule.domain;

import lombok.Getter;
import lombok.Value;

@Value
@Getter
public class Person {
    private final String name;
    private final int age;
}
