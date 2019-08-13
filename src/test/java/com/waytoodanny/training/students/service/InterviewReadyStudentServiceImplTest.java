package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InterviewReadyStudentServiceImplTest {
    @Mock
    private CompanyStudentRepository companyStudentRepository;

    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student("Masha", 5, 5),
            new Student("Gena", 2, 3),
            new Student("Vanya", 3, 4),
            new Student("Sveta", 5, 5),
            new Student("Bodya", 3, 3)
    ));

    @Spy
    private Collection<CompanyStudentFilter> interviewReadyFilters =
                Arrays.asList(new PreLabSufficientMarkFilter(), new WithinLabSufficientMarkFilter());

    @InjectMocks
    private InterviewReadyStudentServiceImpl interviewReadyStudentService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(companyStudentRepository.getAll()).thenReturn(students);
    }

    @Test
    public void getAllReadyForInterviewTestReadyAmount() {
        Collection<Student> students = interviewReadyStudentService.getAllReadyForInterview();
        assertEquals(2, students.size());
    }

    @Test
    public void getAllReadyForInterviewTimeoutTest() {
        InterviewReadyStudentServiceImpl interviewReadyStudentService1 =
                Mockito.spy(new InterviewReadyStudentServiceImpl(companyStudentRepository, interviewReadyFilters));

        doNothing().when(interviewReadyStudentService1).notifyRemoteServiceAboutGreatStudents(anyCollection());
        assertTimeout(Duration.ofMillis(1000), interviewReadyStudentService1::getAllReadyForInterview);
    }
}