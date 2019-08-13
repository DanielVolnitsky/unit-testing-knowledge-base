package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InterviewReadyStudentServiceImplTest {

    @Mock
    private CompanyStudentRepository repository;

    @Spy
    private Collection<CompanyStudentFilter> filters = new ArrayList<>(Arrays.asList(new PreLabSufficientMarkFilter(),
            new WithinLabSufficientMarkFilter()));

    @Spy
    private Collection<CompanyStudentFilter> emptyFilters;


    @InjectMocks
    private InterviewReadyStudentServiceImpl studentService;



    private Collection<Student> dataFromRepository;


    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataFromRepository = new HashSet<Student>() {{
            add(new Student("Alex", 3, 3));
            add(new Student("Masha", 2, 3));
            add(new Student("Dima", 3, 3));
            add(new Student("Vlad", 5, 4));
        }};
        when(repository.getAll()).thenReturn(dataFromRepository);
    }

    @Test
    void getAllReadyForInterview() {
        InterviewReadyStudentServiceImpl spy = spy(new InterviewReadyStudentServiceImpl(repository, filters));
        doNothing().when(spy).notifyRemoteServiceAboutGreatStudents(anyCollection());
        Set<Student> temp = new HashSet<Student>() {{
            add(new Student("Vlad", 5, 4));
        }};
        assertTimeout(Duration.ofMillis(1000), () -> {
            Assertions.assertEquals(temp, spy.getAllReadyForInterview());
        });
    }

    @Test
    void getAllReadyForInterviewRetunAllStudents() {
        InterviewReadyStudentServiceImpl spy = spy(new InterviewReadyStudentServiceImpl(repository, emptyFilters));
        Set<Student> temp = new HashSet<Student>() {{
            add(new Student("Alex", 3, 3));
            add(new Student("Masha", 2, 3));
            add(new Student("Dima", 3, 3));
            add(new Student("Vlad", 5, 4));
        }};
        Assertions.assertEquals(temp, spy.getAllReadyForInterview());

    }
}