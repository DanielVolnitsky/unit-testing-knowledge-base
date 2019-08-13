package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InterviewReadyStudentServiceImplTest {

    @Mock
    private CompanyStudentRepository companyStudentRepository;

    @Spy
    private PreLabSufficientMarkFilter preLabSufficientMarkFilter;

    @Spy
    private WithinLabSufficientMarkFilter withinLabSufficientMarkFilter;

    @Before
    public void setUp() {
        Collection<Student> students = new HashSet<Student>(){{
            add(new Student("Masha", 5, 5));
            add(new Student("Gena", 2, 3));
            add(new Student("Vanya", 3, 4));
            add(new Student("Sveta", 5, 5));
            add(new Student("Bodya", 3, 3));
        }};

        when(companyStudentRepository.getAll()).thenReturn(students);
    }

    @Test(timeout=1000)
    public void getAllReadyForInterviewWithoutFilters() {
        Collection<CompanyStudentFilter> interviewReadyFilters = new ArrayList<>();
        InterviewReadyStudentServiceImpl interviewReadyStudentService =
                Mockito.spy(new InterviewReadyStudentServiceImpl(companyStudentRepository, interviewReadyFilters));

        Collection<Student> allReadyForInterview = interviewReadyStudentService.getAllReadyForInterview();
        assertEquals(5, allReadyForInterview.size());
    }

    @Test(timeout=1000)
    public void getAllReadyForInterviewWithPreLabFilter() {
        Collection<CompanyStudentFilter> interviewReadyFilters =
                Collections.singletonList(preLabSufficientMarkFilter);
        InterviewReadyStudentServiceImpl interviewReadyStudentService =
                Mockito.spy(new InterviewReadyStudentServiceImpl(companyStudentRepository, interviewReadyFilters));

        doNothing().when(interviewReadyStudentService).notifyRemoteServiceAboutGreatStudents(anyCollection());

        Collection<Student> expected = Arrays.asList(
                new Student("Masha", 5, 5),
                new Student("Sveta", 5, 5)
        );

        Collection<Student> actual = interviewReadyStudentService.getAllReadyForInterview();
        assertThat(expected, containsInAnyOrder(actual.toArray()));
    }

    @Test(timeout=1000)
    public void getAllReadyForInterviewWithWithinLabFilter() {
        Collection<CompanyStudentFilter> interviewReadyFilters =
                Collections.singletonList(withinLabSufficientMarkFilter);
        InterviewReadyStudentServiceImpl interviewReadyStudentService =
                Mockito.spy(new InterviewReadyStudentServiceImpl(companyStudentRepository, interviewReadyFilters));

        doNothing().when(interviewReadyStudentService).notifyRemoteServiceAboutGreatStudents(anyCollection());

        Collection<Student> expected = Arrays.asList(
                new Student("Masha", 5, 5),
                new Student("Sveta", 5, 5),
                new Student("Vanya", 3, 4)
        );

        Collection<Student> actual = interviewReadyStudentService.getAllReadyForInterview();
        assertThat(expected, containsInAnyOrder(actual.toArray()));
    }

    @Test(timeout=1000)
    public void getAllReadyForInterviewWithAllFilters() {
        Collection<CompanyStudentFilter> interviewReadyFilters =
                Arrays.asList(preLabSufficientMarkFilter, withinLabSufficientMarkFilter);
        InterviewReadyStudentServiceImpl interviewReadyStudentService =
                Mockito.spy(new InterviewReadyStudentServiceImpl(companyStudentRepository, interviewReadyFilters));

        doNothing().when(interviewReadyStudentService).notifyRemoteServiceAboutGreatStudents(anyCollection());

        Collection<Student> expected = Arrays.asList(
                new Student("Masha", 5, 5),
                new Student("Sveta", 5, 5)
        );

        Collection<Student> actual = interviewReadyStudentService.getAllReadyForInterview();
        assertThat(expected, containsInAnyOrder(actual.toArray()));
    }

}