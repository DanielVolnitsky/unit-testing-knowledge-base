package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    private String givenExpression;

    @Test
    void shouldThrowExceptionByDivisionByZero() {
        givenExpression = "3/0";
        assertThrows(ArithmeticException.class,
                () -> calculator.calculate(givenExpression));
    }

    @Test
    void shouldReturnCorrectMessageWhenThrowIllegalArgumentExceptionWithWrongArgument() {
        givenExpression = "2/a";
        Exception expectedException = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(givenExpression));

        String actualMessage = expectedException.getMessage();
        String expectedMessage = "Invalid operation: a";
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenInputCharacter() {
        givenExpression = "2/e";
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(givenExpression));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenInputWrongBiOperation() {
        givenExpression = "/";
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(givenExpression));
    }

    @Test
    void shouldReturnCorrectMessageWhenThrowIllegalArgumentExceptionWithWrongBinaryOperation(){
        givenExpression = "/";
        Exception expectedException = assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(givenExpression));

        String actualMessage = expectedException.getMessage();
        System.out.println(actualMessage);
        String expectedMessage = "Invalid expression provided";
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldReturnCorrectResultWhenMultiplyIntegers() {
        givenExpression = "3*3";
        Integer expectedResult = 9;
        Integer actualResult = calculator.calculate(givenExpression);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnCorrectResultWhenSubtractIntegers() {
        givenExpression = "4-8";
        Integer expectedResult = -4;
        Integer actualResult = calculator.calculate(givenExpression);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnCorrectResultWhenPlusIntegers() {
        givenExpression = "4+8";
        Integer expectedResult = 12;
        Integer actualResult = calculator.calculate(givenExpression);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnCorrectResultWhenDivideIntegers() {
        givenExpression = "8/4";
        Integer expectedResult = 2;
        Integer actualResult = calculator.calculate(givenExpression);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldReturnCorrectResultWithPriorityBranches(){
        givenExpression = "(3+4)*8/4";
        Integer expectedResult = 14;
        Integer actualResult = calculator.calculate(givenExpression);
        assertEquals(expectedResult, actualResult);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "3/3*(6+6)=12",
            "(5+2)*7=49",
    }, delimiter = '=')
    void shouldReturnCorrectResultWithPriorityBranches(String expression, Integer expectedResult) {
        Integer actualResult = calculator.calculate(expression);
        assertEquals(expectedResult, actualResult);
    }
}
