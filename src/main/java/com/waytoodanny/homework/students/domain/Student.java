package com.waytoodanny.homework.students.domain;

import lombok.Value;

@Value
public class Student {
    private final String name;
    private final int preLabMark;
    private final int withinLabMark;
}
