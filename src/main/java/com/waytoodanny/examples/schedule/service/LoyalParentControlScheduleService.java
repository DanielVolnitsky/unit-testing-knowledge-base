package com.waytoodanny.examples.schedule.service;

import com.waytoodanny.examples.schedule.domain.Schedule;
import com.waytoodanny.examples.schedule.domain.thingstodo.ThingToDo;
import lombok.Value;

import java.util.Objects;

@Value
public class LoyalParentControlScheduleService implements ScheduleService {

    private final ScheduleService delegate;

    @Override
    public void persist(Schedule schedule) {
        if(Objects.isNull(schedule)){
            throw new IllegalArgumentException("Schedule can not be null");
        }
        if(!isPersonAdult(schedule) && scheduleContainsSuperAdultThings(schedule)){
            throw new IllegalArgumentException("You'd better not do that...");
        }
        System.out.println("Okay, nice plan!");
        delegate.persist(schedule);
    }

    private boolean isPersonAdult(Schedule schedule) {
        return schedule.getPerson().getAge() > 18;
    }

    private boolean scheduleContainsSuperAdultThings(Schedule schedule) {
        return schedule.getThingsToDo()
                .stream()
                .anyMatch(t -> t.getRate().equals(ThingToDo.AdultRate.HIGH));
    }
}
