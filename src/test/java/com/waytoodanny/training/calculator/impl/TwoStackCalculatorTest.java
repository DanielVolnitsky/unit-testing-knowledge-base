package com.waytoodanny.training.calculator.impl;

import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    private String given;
    private double expected;
    private double actual;

    @Test
    void calculate_givenCorrectSumOperation_returnsCorrectResult() {
        given = "6+4";
        expected = 10;
        actual = calculator.calculate(given);
        assertEquals(calculator.calculate(given), expected);
    }

    @Test
    void calculate_givenCorrectDeductionOperation_returnsCorrectResult() {
        given = "6-4";
        expected = 2;
        actual = calculator.calculate(given);
        assertEquals(calculator.calculate(given), expected);
    }

    @Test
    void calculate_givenCorrectMultiplyingOperation_returnsCorrectResult() {
        String given = "6*4";
        double expected = 24;
        double actual = calculator.calculate(given);
        assertEquals(calculator.calculate(given), expected);
    }

    @Test
    void calculate_givenCorrectDivideOperation_returnsCorrectResult() {
        given = "8/4";
        expected = 2;
        actual = calculator.calculate(given);
        assertEquals(calculator.calculate(given), expected);
    }

    @Test
    void calculate_givenCorrectComplexOperation_returnsCorrectResult() {
        given = "(6+4)*(44/11)";
        expected = 40;
        actual = calculator.calculate(given);
        assertEquals(calculator.calculate(given), expected);
    }

    @Test
    void calculate_givenDivisionByZero_returnArithmeticException() {
        given = "6/0";
        assertThrows(ArithmeticException.class, () -> calculator.calculate(given));

    }

    @Test
    void calculate_givenSingleOperatorExpression_returnIllegalArgumentExceptionWithCorrectMessage() {
        String given = "+";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(given));
        String actual = exception.getMessage();
        String expected = "Invalid expression provided";
        assertEquals(expected, actual);
    }

    @Test
    void calculate_givenOperationWithNonDigit_returnIllegalArgumentExceptionWithCorrectMessage() {
        String given = "2/a";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(given));
        String actual = exception.getMessage();
        String expected = "Invalid operation: a";
        assertEquals(expected, actual);
    }

    @Test
    void calculate_givenCorrectNotNestedOperation_returnsCorrectResult() {
        given = "8/4*2+3";
        expected = 7;
        actual = calculator.calculate(given);
        assertEquals(expected, actual);
    }
}