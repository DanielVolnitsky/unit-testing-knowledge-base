package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.domain.filters.PreLabSufficientMarkFilter;
import com.waytoodanny.training.students.domain.filters.WithinLabSufficientMarkFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class InterviewReadyStudentServiceImplTest {

  private static final List<Student> testStudents = Arrays.asList(
          new Student("Ollie", 1, 2),
          new Student("Tyler", 2, 3),
          new Student("Gabriel", 3, 1),
          new Student("Michael", 1, 5),
          new Student("Aarav", 4, 2),
          new Student("Westin", 5, 4),
          new Student("Reuben", 3, 3)
  );
  private static final List<Student> preLabStudents =
          Arrays.asList(testStudents.get(4), testStudents.get(5));
  private static final List<Student> withinLabStudents =
          Arrays.asList(testStudents.get(3), testStudents.get(5));
  private static final List<Student> bothFiltersStudents =
          Arrays.asList(testStudents.get(3), testStudents.get(4), testStudents.get(5));

  private InterviewReadyStudentServiceImpl preLabService;
  private InterviewReadyStudentServiceImpl withinLabService;
  private InterviewReadyStudentServiceImpl bothFiltersService;

  @BeforeEach
  void init() {
    CompanyStudentFilter preLabFilter = new PreLabSufficientMarkFilter();
    CompanyStudentFilter withinLabFilter = new WithinLabSufficientMarkFilter();

    MockitoAnnotations.initMocks(this);
    CompanyStudentRepository repository = mock(CompanyStudentRepository.class);
    when(repository.getAll()).thenReturn(testStudents);

    preLabService = spy(
            new InterviewReadyStudentServiceImpl(repository, Collections.singleton(preLabFilter)));
    withinLabService = spy(
            new InterviewReadyStudentServiceImpl(repository, Collections.singleton(withinLabFilter)));
    bothFiltersService = spy(
            new InterviewReadyStudentServiceImpl(repository, Arrays.asList(preLabFilter, withinLabFilter)));

    doNothing().when(preLabService).notifyRemoteServiceAboutGreatStudents(anyCollectionOf(Student.class));
    doNothing().when(withinLabService).notifyRemoteServiceAboutGreatStudents(anyCollectionOf(Student.class));
    doNothing().when(bothFiltersService).notifyRemoteServiceAboutGreatStudents(anyCollectionOf(Student.class));
  }

  @Test
  void getAllReadyForInterview() {
    assertEquals(new HashSet<>(preLabStudents), new HashSet<>(preLabService.getAllReadyForInterview()));
    assertEquals(new HashSet<>(withinLabStudents), new HashSet<>(withinLabService.getAllReadyForInterview()));
    assertEquals(new HashSet<>(bothFiltersStudents), new HashSet<>(bothFiltersService.getAllReadyForInterview()));
  }
}