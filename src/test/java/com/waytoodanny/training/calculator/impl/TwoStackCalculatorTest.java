package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TwoStackCalculatorTest {
    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @ParameterizedTest
    @CsvSource({
            "2+2, 4",
            "2*3, 6",
            "2/2, 1",
            "5-4, 1",
            "3, 3"
    })
    void shouldCalculateSimpleExpressionsCorrect(String expression, double result) {
        double actual = calculator.calculate(expression);
        assertEquals(result, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2+", "+", "5%3"})
    void shouldThrowIllegalArgumentExceptionOnIncorrectInputs(String expression) {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2/0", "8/(6-2*3)"})
    void shouldThrowArithmeticExceptionOnDivisionByZero(String expression) {
        assertThrows(ArithmeticException.class, () -> calculator.calculate(expression));
    }

    @ParameterizedTest
    @CsvSource({
            "2+2*2, 6",
            "2*3-2, 4",
            "20+2/2*3-8, 15"
    })
    void shouldCalculateExpressionsWithOperandsWithDifferentPriorityCorrect(String expression, double result) {
        double actual = calculator.calculate(expression);
        assertEquals(result, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "8/2*(2+2), 16",
            "(3+2)*3, 15",
            "3*(2+3), 15",
            "8/(2*2), 2"
    })
    void shouldCalculateExpressionsWithBracketsCorrect(String expression, double result) {
        double actual = calculator.calculate(expression);
        assertEquals(result, actual);
    }
}