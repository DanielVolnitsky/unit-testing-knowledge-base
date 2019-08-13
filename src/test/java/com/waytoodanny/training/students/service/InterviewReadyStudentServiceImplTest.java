package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class InterviewReadyStudentServiceImplTest {
    @Mock
    CompanyStudentRepository repository;

    private InterviewReadyStudentServiceImpl interviewReadyStudentService;
    private List<Student> students;
    private Collection<Student> resultStudents;
    private Collection<CompanyStudentFilter> companyStudentFilters = new ArrayList<>();

    @BeforeEach
    void setUp() {
        students = Arrays.asList(
                new Student("Alex", 4, 5),
                new Student("Gena", 2, 2),
                new Student("Vitia", 5, 5),
                new Student("Lena", 3, 4),
                new Student("Sveta", 3, 3),
                new Student("Bob", 2, 4));

        when(repository.getAll()).thenReturn(students);
    }


    @Test
    public void shouldReturnAllStudentsWhenFilterCollectionEmpty() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            interviewReadyStudentService = new InterviewReadyStudentServiceImpl(repository, companyStudentFilters);
            resultStudents = interviewReadyStudentService.getAllReadyForInterview();
            interviewReadyStudentService.getAllReadyForInterview();
            assertEquals(students, resultStudents);
        });
    }

    @Test
    void shouldReturnStudentsHashSetWhenWithinLabFilterApplied() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            companyStudentFilters = Collections.singletonList(new WithinLabSufficientMarkFilter());
            interviewReadyStudentService = spy(new InterviewReadyStudentServiceImpl(repository, companyStudentFilters));
            Collection<Student> expectedStudent = new HashSet<>(Arrays.asList(
                    new Student("Lena", 3, 4),
                    new Student("Bob", 2, 4),
                    new Student("Alex", 4, 5),
                    new Student("Vitia", 5, 5)));

            doNothing().when(interviewReadyStudentService).notifyRemoteServiceAboutGreatStudents(anyCollection());
            Collection<Student> resultStudents = interviewReadyStudentService.getAllReadyForInterview();

            assertEquals(expectedStudent, resultStudents);
        });
    }

    @Test
    void shouldReturnStudentsHashSetWhenPreLabFilterApplied() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            companyStudentFilters = Collections.singletonList(new PreLabSufficientMarkFilter());
            interviewReadyStudentService = spy(new InterviewReadyStudentServiceImpl(repository, companyStudentFilters));
            Collection<Student> expectedStudent = new HashSet<>(Arrays.asList(
                    new Student("Alex", 4, 5),
                    new Student("Vitia", 5, 5)));

            doNothing().when(interviewReadyStudentService).notifyRemoteServiceAboutGreatStudents(anyCollection());
            Collection<Student> resultStudents = interviewReadyStudentService.getAllReadyForInterview();

            assertEquals(expectedStudent, resultStudents);
        });
    }
}