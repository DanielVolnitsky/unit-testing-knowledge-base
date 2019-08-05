package com.waytoodanny.examples.schedule.domain.thingstodo;

public class DrinkAlcohol implements ThingToDo {

    @Override
    public AdultRate getRate() {
        return AdultRate.MEDIUM;
    }
}
