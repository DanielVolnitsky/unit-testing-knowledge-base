package com.waytoodanny.training.students.repository;


import com.waytoodanny.training.students.domain.Student;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Collection;

//do not test me, I just imitate possible real repository call
public class CompanyStudentRepositoryImpl implements CompanyStudentRepository {

    @SneakyThrows
    @Override
    public Collection<Student> getAll() {
        Thread.sleep(2000); //Getting all of them could really be slow :)
        return Arrays.asList(
                new Student("Masha", 5, 5),
                new Student("Gena", 2, 3),
                new Student("Vanya", 3, 4),
                new Student("Sveta", 5, 5),
                new Student("Bodya", 3, 3)
        );
    }
}
