package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InterviewReadyStudentServiceImplTest {

    @Mock
    private CompanyStudentRepositoryImpl repository;

    private Collection<CompanyStudentFilter> interviewReadyFilters = new ArrayList<>();

    private InterviewReadyStudentServiceImpl interviewReadyStudentService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        interviewReadyStudentService =
                Mockito.spy(new InterviewReadyStudentServiceImpl(repository, interviewReadyFilters));

        Mockito.when(repository.getAll()).thenReturn(Arrays.asList(
                new Student("Masha", 5, 5),
                new Student("Gena", 2, 3),
                new Student("Vanya", 3, 4),
                new Student("Sveta", 5, 5),
                new Student("Bodya", 3, 3)
        ));
                doNothing().when(interviewReadyStudentService)
                        .notifyRemoteServiceAboutGreatStudents(anyCollection());
    }


    @Test
    void ShouldReturnAllStudentsWithoutAddingFilter() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            Collection<Student> result = interviewReadyStudentService.getAllReadyForInterview();
            Collection<Student> expected = repository.getAll();
            assertEquals(expected, result);
            verify(repository, times(2)).getAll();
        });

    }

    @Test
    void shouldReturnFilteredInterviewReadyStudents() {
        interviewReadyFilters.add(new PreLabSufficientMarkFilter());
        interviewReadyFilters.add(new WithinLabSufficientMarkFilter());

        assertTimeout(Duration.ofMillis(1000), () -> {
            Collection<Student> result = interviewReadyStudentService.getAllReadyForInterview();
            Collection<Student> expected = Arrays.asList(
                    new Student("Sveta", 5, 5),
                    new Student("Vanya", 3, 4),
                    new Student("Masha", 5, 5)
            );
            assertIterableEquals(expected, result);
            verify(repository, times(1)).getAll();
        });
    }
}