package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @ParameterizedTest
    @CsvSource(value = {"0+0=0", "2+1=3", "2-1=1", "5-7=-2", "3*2=6", "100/5=20"}, delimiter = '=')
    void shouldCorrectCalculateSimpleParameterizedOperations(String expression, double expected) {
        double actual = calculator.calculate(expression);
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowArithmeticExceptionDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.calculate("10/0"));
    }


    @Test
    void shouldCorrectCalculateExpressionsWithOperandsWithDifferentPriority() {
        double actual = calculator.calculate("15-4+3*20");
        assertEquals(71, actual);
    }

    @Test
    void shouldCorrectCalculateExpressionsWithParentheses() {
        double actual = calculator.calculate("(15-20)*3");
        assertEquals(-15, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"foo", "2a+5", "12/*5"})
    void shouldThrowIllegalArgumentExceptionOnIncorrectInputs(String expression) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(expression));
    }
}