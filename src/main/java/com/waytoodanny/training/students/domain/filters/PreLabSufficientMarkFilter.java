package com.waytoodanny.training.students.domain.filters;

import com.waytoodanny.training.students.domain.Student;
import lombok.SneakyThrows;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class PreLabSufficientMarkFilter implements CompanyStudentFilter {

    @SneakyThrows
    @Override
    public Collection<Student> apply(Collection<Student> students) {
        return students.stream()
                .filter(s -> s.getPreLabMark() > 3)
                .collect(toList());
    }
}
