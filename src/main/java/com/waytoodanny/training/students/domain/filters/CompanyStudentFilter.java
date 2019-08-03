package com.waytoodanny.training.students.domain.filters;

import com.waytoodanny.training.students.domain.Student;

import java.util.Collection;
import java.util.function.UnaryOperator;

public interface CompanyStudentFilter extends UnaryOperator<Collection<Student>> {

}

