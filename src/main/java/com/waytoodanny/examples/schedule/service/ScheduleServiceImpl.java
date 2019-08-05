package com.waytoodanny.examples.schedule.service;

import com.waytoodanny.examples.schedule.domain.Schedule;
import com.waytoodanny.examples.schedule.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository repository;

    @Override
    public void persist(Schedule schedule) {
        this.repository.save(schedule);
        logCreatingToRemote();
    }

    @SneakyThrows
    public void logCreatingToRemote() {
        Thread.sleep(1000);
        //sending log to remote
    }
}
