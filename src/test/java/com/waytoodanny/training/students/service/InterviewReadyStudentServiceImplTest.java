package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class InterviewReadyStudentServiceImplTest {

    private InterviewReadyStudentServiceImpl interviewReadyStudent;
    private Collection<CompanyStudentFilter> interviewReadyFilters ;
    private List<Student> students;

    @Mock
    private CompanyStudentRepository repository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);

        interviewReadyFilters = new ArrayList<>();
        interviewReadyStudent =
                Mockito.spy(new InterviewReadyStudentServiceImpl(repository, interviewReadyFilters));

        students = (Arrays.asList(
                new Student("Masha", 5, 5),
                new Student("Gena", 2, 3),
                new Student("Vanya", 3, 4),
                new Student("Sveta", 5, 5),
                new Student("Bodya", 3, 3)
        ));

        when(repository.getAll()).thenReturn(students);
        doNothing().when(interviewReadyStudent).notifyRemoteServiceAboutGreatStudents(anyCollection());
    }


    @Test
    void shouldReturnAllStudentsReadyForInterviewWithoutFilter() {
        assertTimeout(Duration.ofMillis(1000), () -> {
//            //write your test logic here
            Collection<Student> expectedStudents = Arrays.asList(
                    new Student("Masha", 5, 5),
                    new Student("Gena", 2, 3),
                    new Student("Vanya", 3, 4),
                    new Student("Sveta", 5, 5),
                    new Student("Bodya", 3, 3)
            );

            Collection<Student> actualStudents = interviewReadyStudent.getAllReadyForInterview();
            assertEquals(expectedStudents,actualStudents);
        });
    }

    @Test
    void shouldReturnAllStudentsReadyForInterviewWithFilter(){
        interviewReadyFilters.add(new PreLabSufficientMarkFilter());
        interviewReadyFilters.add(new WithinLabSufficientMarkFilter());

        assertTimeout(Duration.ofMillis(1000), () -> {
//            //write your test logic here
            Collection<Student> expectedStudents = Arrays.asList(
                    new Student("Masha", 5, 5),
                    new Student("Vanya", 3, 4),
                    new Student("Sveta", 5, 5)
            );

            System.out.println(expectedStudents);
            Collection<Student> actualStudents = interviewReadyStudent.getAllReadyForInterview();
            System.out.println(actualStudents);
            assertTrue(expectedStudents.containsAll(actualStudents));
        });
    }
}
