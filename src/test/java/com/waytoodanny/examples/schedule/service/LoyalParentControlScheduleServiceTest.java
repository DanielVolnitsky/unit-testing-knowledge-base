package com.waytoodanny.examples.schedule.service;

import com.waytoodanny.examples.schedule.domain.Person;
import com.waytoodanny.examples.schedule.domain.Schedule;
import com.waytoodanny.examples.schedule.domain.thingstodo.GoShopping;
import com.waytoodanny.examples.schedule.domain.thingstodo.ThingToDo;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoyalParentControlScheduleServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Mock
    private ScheduleService delegateMock;

    @InjectMocks
    private LoyalParentControlScheduleService autoInjectedService;

    @Test
    public void persist_givenNonHighRateSchedule_willDelegatePersisting() {
        Schedule schedule = new Schedule(
                new Person("Maksym", 15),
                Arrays.asList(
                        new GoShopping(), //what is wrong here?
                        () -> ThingToDo.AdultRate.LOW, //tricked or not?
                        () -> ThingToDo.AdultRate.LOW
                )
        );

        doNothing().when(delegateMock).persist(any(Schedule.class));

        autoInjectedService.persist(schedule);

        verify(delegateMock).persist(any(Schedule.class));
        verify(delegateMock, times(1)).persist(any(Schedule.class)); //the same as above
    }

    @Test
    public void testPersistScheduleIsNull() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Schedule can not be null");

        LoyalParentControlScheduleService service =
                new LoyalParentControlScheduleService(delegateMock);

        service.persist(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void persist_ScheduleIsNull() {
        LoyalParentControlScheduleService service =
                new LoyalParentControlScheduleService(delegateMock);

        doNothing().when(delegateMock).persist(any(Schedule.class));

        service.persist(null);
    }

    private static class TestHighRateThingToDo implements ThingToDo {

        @Override
        public AdultRate getRate() {
            return AdultRate.HIGH;
        }
    }
}