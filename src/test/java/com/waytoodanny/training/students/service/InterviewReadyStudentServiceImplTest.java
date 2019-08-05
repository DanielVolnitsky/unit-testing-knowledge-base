package com.waytoodanny.training.students.service;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.Mockito.anyCollectionOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class InterviewReadyStudentServiceImplTest {

  private Collection<CompanyStudentFilter> companyStudentFilters = new ArrayList<>();

  private InterviewReadyStudentServiceImpl interviewReadyStudentService;
  private List<Student> students = Arrays.asList(
      new Student("Masha", 5, 5),
      new Student("Gena", 2, 3),
      new Student("Vanya", 3, 4),
      new Student("Sveta", 5, 5),
      new Student("Bodya", 3, 3)
  );

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
    CompanyStudentRepository companyStudentRepository = mock(CompanyStudentRepository.class);
    interviewReadyStudentService = spy(new InterviewReadyStudentServiceImpl(
        companyStudentRepository, companyStudentFilters));

    when(companyStudentRepository.getAll()).thenReturn(students);
    doNothing().when(interviewReadyStudentService)
        .notifyRemoteServiceAboutGreatStudents(anyCollectionOf(Student.class));
  }

  @Test
  void getAllReadyForInterview() {
    assertTimeout(Duration.ofMillis(1000), () -> {
      //test without filters
      Collection<Student> actual = interviewReadyStudentService.getAllReadyForInterview();
      assertIterableEquals(students, actual);

      //test with pre-lab filter
      companyStudentFilters.add(new PreLabSufficientMarkFilter());
      actual = interviewReadyStudentService.getAllReadyForInterview();
      assertIterableEquals(Arrays.asList(students.get(3), students.get(0)), actual);

      //test with within lab filter
      companyStudentFilters.clear();
      companyStudentFilters.add(new WithinLabSufficientMarkFilter());
      actual = interviewReadyStudentService.getAllReadyForInterview();
      assertIterableEquals(Arrays.asList(students.get(3), students.get(2), students.get(0)),
          actual);

      //test with both lab filters
      companyStudentFilters.add(new PreLabSufficientMarkFilter());
      actual = interviewReadyStudentService.getAllReadyForInterview();
      assertIterableEquals(Arrays.asList(students.get(3), students.get(2), students.get(0)),
          actual);
    });
  }
}