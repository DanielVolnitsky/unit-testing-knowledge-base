package com.waytoodanny.examples;

import com.waytoodanny.examples.schedule.domain.Person;
import com.waytoodanny.examples.schedule.domain.Schedule;
import com.waytoodanny.examples.schedule.domain.thingstodo.DrinkAlcohol;
import com.waytoodanny.examples.schedule.domain.thingstodo.GoShopping;
import com.waytoodanny.examples.schedule.domain.thingstodo.LearnFunctionalProgramming;
import com.waytoodanny.examples.schedule.repository.ScheduleRepository;
import com.waytoodanny.examples.schedule.service.LoyalParentControlScheduleService;
import com.waytoodanny.examples.schedule.service.ScheduleService;
import com.waytoodanny.examples.schedule.service.ScheduleServiceImpl;

import java.util.Arrays;

public class Main {

    private static final ScheduleRepository REPOSITORY =
            new ScheduleRepository();

    private static final ScheduleService SERVICE =
            new LoyalParentControlScheduleService(new ScheduleServiceImpl(REPOSITORY));

    public static void main(String[] args) {
        Schedule s = new Schedule(
                new Person("Maksym", 15),
                Arrays.asList(new DrinkAlcohol(), new GoShopping(), new LearnFunctionalProgramming())
        );
        SERVICE.persist(s);
    }
}
