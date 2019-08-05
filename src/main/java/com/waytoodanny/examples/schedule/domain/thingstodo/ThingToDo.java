package com.waytoodanny.examples.schedule.domain.thingstodo;

public interface ThingToDo {
    AdultRate getRate();

    enum AdultRate {
        LOW, MEDIUM, HIGH
    }
}
