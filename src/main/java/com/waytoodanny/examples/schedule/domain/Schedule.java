package com.waytoodanny.examples.schedule.domain;

import com.waytoodanny.examples.schedule.domain.thingstodo.ThingToDo;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode
public class Schedule {
    private final Person person;
    private final List<ThingToDo> thingsToDo;
}
