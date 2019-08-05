package com.waytoodanny.examples.schedule.service;

import com.waytoodanny.examples.schedule.domain.Person;
import com.waytoodanny.examples.schedule.domain.Schedule;
import com.waytoodanny.examples.schedule.domain.thingstodo.ThingToDo;
import lombok.EqualsAndHashCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

//argumentCaptor power below
//ArgumentCaptor is used with Mockito verify() methods to get the arguments passed when any method is called.
@RunWith(MockitoJUnitRunner.class)
public class ExcludingParentControlScheduleServiceTest {

    @Mock
    private ScheduleService delegateMock;

    @Test
    public void persist() {
        ExcludingParentControlScheduleService service =
                new ExcludingParentControlScheduleService(delegateMock, ThingToDo.AdultRate.HIGH);

        Schedule schedule = new Schedule(
                new Person("Maksym", 15),
                Arrays.asList(
                        new LowRateThingToDo(),
                        new MediumRateThingToDo(),
                        new TestHighRateThingToDo()
                )
        );

        ArgumentCaptor<Schedule> scheduleCaptor = ArgumentCaptor.forClass(Schedule.class);

        service.persist(schedule);

        verify(delegateMock).persist(scheduleCaptor.capture());

        Schedule expected = new Schedule(
                new Person("Maksym", 15),
                Arrays.asList(
                        new LowRateThingToDo(),
                        new MediumRateThingToDo()
                )
        );

        assertEquals(expected, scheduleCaptor.getValue());
    }

    @EqualsAndHashCode
    private static class LowRateThingToDo implements ThingToDo {

        @Override
        public AdultRate getRate() {
            return AdultRate.LOW;
        }
    }

    @EqualsAndHashCode
    private static class MediumRateThingToDo implements ThingToDo {

        @Override
        public AdultRate getRate() {
            return AdultRate.MEDIUM;
        }
    }

    @EqualsAndHashCode
    private static class TestHighRateThingToDo implements ThingToDo {

        @Override
        public AdultRate getRate() {
            return AdultRate.HIGH;
        }
    }
}