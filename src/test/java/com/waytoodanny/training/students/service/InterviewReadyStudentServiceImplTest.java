package com.waytoodanny.training.students.service;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

class InterviewReadyStudentServiceImplTest {

    @Test
    void getAllReadyForInterview() {
        assertTimeout(Duration.ofMillis(1000), () -> {
            //write your test logic here
        });
    }
}