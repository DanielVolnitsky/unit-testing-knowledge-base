package com.waytoodanny.training.students.repository;

import com.waytoodanny.training.students.domain.Student;

import java.util.Collection;

public interface CompanyStudentRepository {
    Collection<Student> getAll();
}
