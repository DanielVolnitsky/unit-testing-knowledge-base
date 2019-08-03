package com.waytoodanny.training.students.service;

import com.waytoodanny.training.students.domain.Student;
import com.waytoodanny.training.students.domain.filters.CompanyStudentFilter;
import com.waytoodanny.training.students.repository.CompanyStudentRepository;

import java.util.Collection;
import java.util.HashSet;

public class InterviewReadyStudentServiceImpl implements InterviewReadyStudentService {

    private final CompanyStudentRepository repository;
    private final Collection<CompanyStudentFilter> interviewReadyFilters;

    public InterviewReadyStudentServiceImpl(CompanyStudentRepository repository,
                                            Collection<CompanyStudentFilter> interviewReadyFilters) {
        this.repository = repository;
        this.interviewReadyFilters = interviewReadyFilters;
    }

    @Override
    public Collection<Student> getAllReadyForInterview() {
        Collection<Student> students = repository.getAll();
        if (interviewReadyFilters.size() == 0) {
            return students;
        }

        return interviewReadyFilters.stream()
                .map(f -> f.apply(students))
                .map(HashSet::new)
                .reduce((s1, s2) -> new HashSet<Student>() {{
                    addAll(s1);
                    addAll(s2);
                }})
                .orElseGet(HashSet::new);
    }
}
