package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

public class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIllegalSignArgument() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid operation: =");
        calculator.calculate("20=0");
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenIllegalOperandArgument() throws IllegalArgumentException {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Invalid operation: A");
        calculator.calculate("A+10");
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenInvalidExpression() throws NoSuchElementException {
        thrown.expect(NoSuchElementException.class);
        calculator.calculate(")10)");
    }

    @Test
    public void shouldThrowArithmeticExceptionWhenDeleteByZero() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("/ by zero");
        calculator.calculate("10/0");
    }
}