package com.waytoodanny.training;

import com.waytoodanny.training.calculator.impl.TwoStackCalculatorParameterizedTest;
import com.waytoodanny.training.calculator.impl.TwoStackCalculatorTest;
import com.waytoodanny.training.students.service.InterviewReadyStudentServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TwoStackCalculatorParameterizedTest.class,
        TwoStackCalculatorTest.class,
        InterviewReadyStudentServiceImplTest.class
})
public class TestSuite{

}