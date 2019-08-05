package com.waytoodanny.examples.schedule.service;

import com.waytoodanny.examples.schedule.domain.Schedule;
import com.waytoodanny.examples.schedule.domain.thingstodo.ThingToDo;
import lombok.Value;

import static java.util.stream.Collectors.toList;

@Value
public class ExcludingParentControlScheduleService implements ScheduleService {

    private final ScheduleService delegate;
    private final ThingToDo.AdultRate rateToExclude;

    @Override
    public void persist(Schedule schedule) {
        Schedule filtered = new Schedule(
                schedule.getPerson(),
                schedule.getThingsToDo()
                        .stream()
                        .filter(t -> !t.getRate().equals(rateToExclude))
                        .collect(toList())
        );

        delegate.persist(filtered);
    }
}
