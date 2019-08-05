package com.waytoodanny.examples.schedule.domain.thingstodo;

public class GoShopping implements ThingToDo {

    @Override
    public AdultRate getRate() {
        return AdultRate.LOW;
    }
}
