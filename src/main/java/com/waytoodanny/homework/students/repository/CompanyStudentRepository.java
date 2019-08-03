package com.waytoodanny.homework.students.repository;

import com.waytoodanny.homework.students.domain.Student;

import java.util.Collection;

public interface CompanyStudentRepository {
    Collection<Student> getAll();
}
