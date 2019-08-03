package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;

import java.util.Collection;

public interface InterviewReadyStudentService {
    Collection<Student> getAllReadyForInterview();
}
