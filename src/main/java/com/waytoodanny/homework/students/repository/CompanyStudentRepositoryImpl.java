package com.waytoodanny.homework.students.repository;


import com.waytoodanny.homework.students.domain.Student;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Collection;

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
