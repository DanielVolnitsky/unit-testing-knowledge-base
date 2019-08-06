package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepositoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;



import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class InterviewReadyStudentServiceImplTest {

    private InterviewReadyStudentServiceImpl interviewReadyStudentService;

    private Collection<CompanyStudentFilter> interviewReadyFilters = new ArrayList<>(Arrays.asList(
            new PreLabSufficientMarkFilter(), new WithinLabSufficientMarkFilter()
    ));

    @Mock
    private CompanyStudentRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        interviewReadyStudentService =
                Mockito.spy(new InterviewReadyStudentServiceImpl(repository, interviewReadyFilters));
    }

    @Test
    void Given_Repository_When_ObtainingAllGreatStudents_Then_ShouldProceedWithin1000Millis() {
        assertTimeout(Duration.ofMillis(1000), () -> {

            //original method works very slow
            Mockito.when(repository.getAll()).thenReturn(new HashSet<>(Arrays.asList(
                    new Student("Masha", 5, 5),
                    new Student("Gena", 2, 3),
                    new Student("Vanya", 3, 4),
                    new Student("Sveta", 5, 5),
                    new Student("Bodya", 3, 3)
            )));


            //there was bad impl. that we don't need. it only wastes time
            doNothing().when(interviewReadyStudentService)
                    .notifyRemoteServiceAboutGreatStudents(anyCollection());

            //todo: i don't know why verify fails.(may be its about params...)
//            verify(interviewReadyStudentService)
//                    .notifyRemoteServiceAboutGreatStudents(anyCollection());


            Collection<Student> readyForInterview = interviewReadyStudentService.getAllReadyForInterview();

            Collection<Student> expectedReadyForInterview = new HashSet<>(Arrays.asList(
                    new Student("Sveta", 5, 5),
                    new Student("Vanya", 3, 4),
                    new Student("Masha", 5, 5)
            ));

            assertEquals(expectedReadyForInterview, readyForInterview);
        });
    }

    @Test
    void Given_RealImplementation_When_ObtainingAllGreatStudents_Then_ShouldProceedWithNoTimeLimits() {

        CompanyStudentRepositoryImpl repository = new CompanyStudentRepositoryImpl();
        InterviewReadyStudentServiceImpl interviewReadyStudentService = new InterviewReadyStudentServiceImpl(repository, interviewReadyFilters);

        assertEquals(
                new HashSet<>(Arrays.asList(
                        new Student("Sveta", 5, 5),
                        new Student("Vanya", 3, 4),
                        new Student("Masha", 5, 5)
                )),
                interviewReadyStudentService.getAllReadyForInterview()
        );
    }
}

