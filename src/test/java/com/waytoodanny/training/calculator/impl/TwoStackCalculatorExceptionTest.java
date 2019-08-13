package com.waytoodanny.training.calculator.impl;

import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwoStackCalculatorExceptionTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @Test
    public void calculateTestArithmeticException() {
        Assertions.assertThrows(ArithmeticException.class,
                                () -> calculator.calculate("15/0"),
                                "/ by zero");
    }

    @Test
    public void calculateTestIllegalOperandException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                                () -> calculator.calculate("15&0"),
                        "Invalid expression provided");
    }

    @Test
    public void calculateTestIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("Test+5"),
                "Invalid operation: Test");
    }
}
