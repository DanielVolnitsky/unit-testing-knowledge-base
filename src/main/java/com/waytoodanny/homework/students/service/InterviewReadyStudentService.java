package com.waytoodanny.homework.students.service;

import com.waytoodanny.homework.students.domain.Student;

import java.util.Collection;

public interface InterviewReadyStudentService {
    Collection<Student> getAllReadyForInterview();
}
