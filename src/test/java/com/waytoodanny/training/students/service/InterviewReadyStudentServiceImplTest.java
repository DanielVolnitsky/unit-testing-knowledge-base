package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InterviewReadyStudentServiceImplTest {

    @Mock
    private CompanyStudentRepository repository;

    @Spy
    private Collection<CompanyStudentFilter> interviewReadyFilters = new ArrayList<>(
            Arrays.asList(new PreLabSufficientMarkFilter(), new WithinLabSufficientMarkFilter()));

    private Collection<Student> allStudents;

    @InjectMocks
    private InterviewReadyStudentServiceImpl interviewReadyStudentService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        allStudents = new ArrayList<>(
                Arrays.asList(
                        new Student("Masha", 5, 5),
                        new Student("Gena", 2, 3),
                        new Student("Vanya", 3, 4),
                        new Student("Sveta", 5, 5),
                        new Student("Bodya", 3, 3)
                )
        );

        when(repository.getAll()).thenReturn(allStudents);
    }

    @Test
    void getAllReadyForInterview() {
        InterviewReadyStudentServiceImpl spy = Mockito.spy(new InterviewReadyStudentServiceImpl(repository, interviewReadyFilters));
        doNothing().when(spy).notifyRemoteServiceAboutGreatStudents(anyCollection());

        assertTimeout(Duration.ofMillis(1000), spy::getAllReadyForInterview);
    }

    @Test
    void getAmountOfStudentReadyForInterview() {
        Assertions.assertEquals(2, interviewReadyStudentService.getAllReadyForInterview().size());
    }
}