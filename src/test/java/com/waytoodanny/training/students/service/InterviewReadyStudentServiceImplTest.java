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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class InterviewReadyStudentServiceImplTest {

    @Mock
    private CompanyStudentRepository repository;

    private InterviewReadyStudentServiceImpl interviewReadyStudentService;

    private Collection<Student> studentsListFromRepo = new ArrayList<>();
    private Collection<CompanyStudentFilter> interviewReadyFilters = new ArrayList<>();
    private Collection<Student> resultStudents;

    @BeforeEach
    public void setUp() {
        studentsListFromRepo.add(new Student("Masha", 5, 5));
        studentsListFromRepo.add(new Student("Gena", 2, 3));
        studentsListFromRepo.add(new Student("Vanya", 3, 4));
        studentsListFromRepo.add(new Student("Sveta", 5, 5));
        studentsListFromRepo.add(new Student("Bodya", 2, 3));

        when(repository.getAll()).thenReturn(studentsListFromRepo);
    }

    @Test
    public void shouldReturnAllStudentsWhenFilterCollectionEmpty() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            interviewReadyStudentService = new InterviewReadyStudentServiceImpl(repository, interviewReadyFilters);
            resultStudents = interviewReadyStudentService.getAllReadyForInterview();
            interviewReadyStudentService.getAllReadyForInterview();
            assertEquals(studentsListFromRepo, resultStudents);
        });
    }

    @Test
    void shouldReturnStudentsHashSetWhenAllFiltersPresent() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            interviewReadyFilters = Arrays.asList(new WithinLabSufficientMarkFilter(), new PreLabSufficientMarkFilter());
            interviewReadyStudentService = Mockito.spy(new InterviewReadyStudentServiceImpl(repository, interviewReadyFilters));

            Collection<Student> expectedStudent = new HashSet<>();
            expectedStudent.add(new Student("Masha", 5, 5));
            expectedStudent.add(new Student("Vanya", 3, 4));
            expectedStudent.add(new Student("Sveta", 5, 5));

            Mockito.doNothing().when(interviewReadyStudentService).notifyRemoteServiceAboutGreatStudents(anyCollection());
            Collection<Student> resultStudents = interviewReadyStudentService.getAllReadyForInterview();

            assertEquals(expectedStudent, resultStudents);
        });
    }


}