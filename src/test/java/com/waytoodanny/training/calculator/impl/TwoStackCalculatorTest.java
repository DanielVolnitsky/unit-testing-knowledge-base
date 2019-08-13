package com.waytoodanny.training.calculator.impl;


import com.waytoodanny.training.calculator.util.CalculatorUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TwoStackCalculatorTest {

    private final TwoStackCalculator calculator =
            new TwoStackCalculator(CalculatorUtil.BASIC_OPERATIONS);

    @Test
    void addition() {
        Assertions.assertEquals(3, calculator.calculate("1+2"));
    }

    @Test
    void subtraction() {
        Assertions.assertEquals(-1, calculator.calculate("1-2"));
    }

    @Test
    void division() {
        Assertions.assertEquals(0, calculator.calculate("1/2"));
    }

    @Test
    void multiplication() {
        Assertions.assertEquals(2, calculator.calculate("1*2"));
    }

    @Test
    void calculateWithWrongParameter() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("String"),
                "Invalid expression provided");
    }
}