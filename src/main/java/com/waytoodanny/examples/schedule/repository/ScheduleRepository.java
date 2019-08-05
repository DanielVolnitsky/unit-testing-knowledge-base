package com.waytoodanny.examples.schedule.repository;

import com.waytoodanny.examples.schedule.domain.Schedule;
import lombok.SneakyThrows;

import java.util.Random;


public class ScheduleRepository {

    @SneakyThrows
    public boolean save(Schedule schedule) {
        Thread.sleep(500);
        return new Random().nextBoolean();
    }
}
