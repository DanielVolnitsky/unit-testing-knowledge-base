package com.waytoodanny.training.students.domain.filters;

import com.waytoodanny.training.students.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WithinLabSufficientMarkFilterTest {

    private CompanyStudentFilter filter;
    private List<Student> studentsList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        studentsList.add(new Student("Masha", 5, 5));
        studentsList.add(new Student("Gena", 2, 3));
        studentsList.add(new Student("Vanya", 3, 4));
        studentsList.add(new Student("Sveta", 5, 5));
        studentsList.add(new Student("Bodya", 2, 3));
        filter = new WithinLabSufficientMarkFilter();
    }

    @Test
    public void shouldReturnFilteredList() {
        Collection<Student> expectedStudents = new ArrayList<>();
        expectedStudents.add(new Student("Masha", 5, 5));
        expectedStudents.add(new Student("Vanya", 3, 4));
        expectedStudents.add(new Student("Sveta", 5, 5));

        Collection<Student> resultStudents = filter.apply(studentsList);

        assertEquals(resultStudents, expectedStudents);
    }
}